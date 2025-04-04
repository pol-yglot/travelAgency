package com.example.travelagency.faker;

import jakarta.servlet.http.HttpSession;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc : 랜덤 관리자 아이디 관리 class
 * */
public class RandomAdminLogin {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomAdminLogin.class);
    private static final Map<String, Long> adminInfos = new ConcurrentHashMap<>();
    private static final long EXPIRATION_TIME = 1800 * 1000; // 30분

    /**
     * 랜덤 관리자 아이디와 유효 시간을 맵에 저장
     * @param session 세션 객체
     * @return 생성된 관리자 아이디
     * */
    public static String generateAdminId(HttpSession session) {
        Faker faker = new Faker();
        String adminId = faker.pokemon().name().trim();

        String regex = "[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(adminId);

        if (matcher.find()) {
            adminId = adminId.replaceAll(regex, "");
            System.out.println("replaceStr = " + adminId);
        }
        adminInfos.put(adminId, System.currentTimeMillis() + EXPIRATION_TIME);

        session.setAttribute("adminId", adminId);
        return adminId;
    }
    /**
     * 입력된 아이디로 로그인 처리하고, 세션 아이디와 비교
     *
     * @param inputId 입력된 관리자 아이디
     * @param session 세션 객체
     * @return 로그인 성공 여부 (true: 성공, false: 실패)
     */
    public static boolean login(String inputId, HttpSession session) {
        String sessionId = (String) session.getAttribute("adminId");

        if (sessionId != null && sessionId.equals(inputId)) {
            try {
                long expirationTime = adminInfos.get(inputId);
                if (System.currentTimeMillis() < expirationTime) {
                    adminInfos.remove(inputId);
                    session.removeAttribute("adminId");
                    return true;
                } else {
                    adminInfos.remove(inputId);
                    session.removeAttribute("adminId");
                }
            } catch (NullPointerException e) {
                LOGGER.error("inputId not found in adminInfos map: {}", inputId, e);
            }
        }
        return false;
    }
}
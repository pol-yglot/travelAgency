package com.example.travelagency.web;

import com.example.travelagency.faker.RandomAdminLogin;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if(session.getAttribute("adminId") == null) {
            String adminId = RandomAdminLogin.generateAdminId(session);
            LOGGER.debug("랜덤 관리자 아이디 생성 :::::: {}",adminId);
        }else{
            LOGGER.debug("세션 랜덤 관리자 아이디 :::::: {}",session.getAttribute("adminId"));
        }
        return "user/login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String inputId, @RequestParam(required = false) String password, HttpSession session, Model model) {
        if (password == null || password == "") { // 관리자 로그인
            if (RandomAdminLogin.login(inputId, session)) {
                LOGGER.info("관리자로 임시 로그인 성공!");
                return "redirect:/";
            } else {
                LOGGER.warn("관리자로 임시 로그인 실패!");
                model.addAttribute("error", "아이디가 일치하지 않습니다.");
                return "user/login";
            }
        } else { // 사용자 로그인
            try {
                
                // 미가입고객
                if(!userService.isUserExist(inputId)){
                    LOGGER.warn("없는 고객!");
                    model.addAttribute("error", "회원 정보가 없습니다. 회원가입을 진행해주세요.");
                    return "user/signup"; 
                }

                // 가입고객
                UserVO user = userService.getUser(inputId);

                // 비밀번호 해싱 비교 필요
                if (user != null && user.getUSER_ACCOUNT().equals(inputId) && user.getUSER_PASSWORD().equals(password)) {
                    session.setAttribute("user", user); // 세션에 사용자 정보 저장
                    LOGGER.info("사용자 로그인 성공!");
                    return "redirect:/";
                } else {
                    LOGGER.warn("사용자 로그인 실패!");
                    model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
                    return "user/login";
                }
            } catch (Exception e) {
                LOGGER.error("로그인 중 오류 발생: {}", e.getMessage());
                model.addAttribute("error", "로그인 중 오류가 발생했습니다.");
                return "user/login";
            }
        }
    }

    /**
     * 로그아웃을 처리
     * @param session 세션 객체
     * @return 로그아웃 성공 메시지
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 만료
        return "redirect:/";
    }

}

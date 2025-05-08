package com.example.travelagency.config;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import com.example.travelagency.web.UserController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 1) 로그인 폼에서 넘어온 username 파라미터
        String username = request.getParameter("username");
        if (username != null && !username.isBlank()) {
            UserVO user = userService.getUser(username);
            if (user != null) {
                // 2) 실패 카운트 증가
                int failures = user.getLOGIN_FAILED_CNT() == 0 ? 1 : user.getLOGIN_FAILED_CNT() + 1;
                user.setLOGIN_FAILED_CNT(failures);

                // 3) 10회 이상이면 계정 비활성화
                if (failures >= 10) {
                    user.setUSE_YN('N');
                }

                // 4) 변경된 사용자 정보 DB에 반영
                userService.updateUser(user);
            }
        }

        // 5) 다시 로그인 페이지로 redirection
        String errorMsg = "아이디 또는 비밀번호가 올바르지 않습니다.";
        String encoded = URLEncoder.encode(errorMsg, StandardCharsets.UTF_8);

        // /login?error=…
        response.sendRedirect(request.getContextPath() + "/login?error=" + encoded);
    }
}

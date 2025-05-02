package com.example.travelagency.config;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    private final SessionRegistry sessionRegistry;

    public CustomAuthenticationSuccessHandler(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // 세션중복방지
        sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 1) 로그인 폼에서 넘어온 username 파라미터
        String username = request.getParameter("username");
        if (username != null && !username.isBlank()) {
            UserVO user = userService.getUser(username);
            if (user != null) {
                // 2) 실패 카운트 초기화
                int failures = 0;
                user.setLOGIN_FAILED_CNT(failures);

                // 4) 변경된 사용자 정보 DB에 반영
                userService.updateUser(user);
            }
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

package com.example.travelagency.web;

import com.example.travelagency.faker.RandomAdminLogin;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        /*
        if(session.getAttribute("adminId") == null) {
            String adminId = RandomAdminLogin.generateAdminId(session);
            LOGGER.debug("랜덤 관리자 아이디 생성 :::::: {}",adminId);
        }else{
            LOGGER.debug("세션 랜덤 관리자 아이디 :::::: {}",session.getAttribute("adminId"));
        }*/
        return "user/login";
    }

    @GetMapping("/keepAlive")
    @ResponseBody
    public ResponseEntity<Void> keepAlive(HttpSession session) {
        // 빈 요청만으로 세션 타임아웃 리셋
        return ResponseEntity.ok().build();
    }


}

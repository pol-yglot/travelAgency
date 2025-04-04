package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String userList() {
        return "user/userList";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        // 로그인 시 세션에 올린 사용자 정보를 재사용
        UserVO user = (UserVO) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/updateProfile")
    public String updateProfile() {
        return "user/updateProfile";
    }
}

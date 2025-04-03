package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/userList")
    public String userList() {
        List<UserVO> userList = userService.getAllUser();
        return "user/userList";
    }
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "user/logout";
    }

    @GetMapping("/profile")
    public String profile() {
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

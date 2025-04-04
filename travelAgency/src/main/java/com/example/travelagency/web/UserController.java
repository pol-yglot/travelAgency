package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/logout")
    public String logout() {
        return "/home";
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

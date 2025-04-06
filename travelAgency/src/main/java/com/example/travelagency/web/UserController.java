package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserDetailVO;
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
        if(session.getAttribute("user") != null) {
            UserVO user = (UserVO) session.getAttribute("user");
            // USER_ID로 상세정보 조회
            UserDetailVO userDetail = userService.getUserDetail(user.getUSER_ID());
            LOGGER.debug("userDetail = " + userDetail);
            model.addAttribute("user", user);
            model.addAttribute("userDetail", userDetail);
        }else{
            // 미로그인시 로그인 페이지로 이동
            return "user/login";
        }
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/updateProfile")
    public String updateProfile(HttpSession session, Model model) {
        // 로그인 시 세션에 올린 사용자 정보를 재사용
        if(session.getAttribute("user") != null) {
            UserVO user = (UserVO) session.getAttribute("user");
            // USER_ID로 상세정보 조회
            UserDetailVO userDetail = userService.getUserDetail(user.getUSER_ID());
            LOGGER.debug("userDetail = " + userDetail);
            model.addAttribute("user", user);
            model.addAttribute("userDetail", userDetail);
        }else{
            // 미로그인시 로그인 페이지로 이동
            return "user/login";
        }
        return "user/updateProfile";
    }
}

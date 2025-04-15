package com.example.travelagency.web;

import com.example.travelagency.faker.RandomAdminLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    // 사용자 관리
    // 대시보드
    // 권한관리 
    // 카테고리관리

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/userList")
    public String userList() {
        return "user/userList";
    }

    @GetMapping("/report")
    public String report() {
        return "admin/report";
    }

}

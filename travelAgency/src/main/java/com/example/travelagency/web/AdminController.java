package com.example.travelagency.web;

import com.example.travelagency.faker.RandomAdminLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/report")
    public String report() {
        return "admin/report";
    }

}

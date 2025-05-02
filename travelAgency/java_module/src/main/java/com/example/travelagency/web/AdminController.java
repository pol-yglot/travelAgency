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

    @GetMapping("/views")
    public String views(@RequestParam("name") String name) {
        if(name.equals("components")) {
            return "admin/components";
        }
        if(name.equals("forms")) {
            return "admin/forms";
        }
        if(name.equals("icons")) {
            return "admin/icons";
        }
        if(name.equals("notifications")) {
            return "admin/notifications";
        }
        if(name.equals("tables")) {
            return "admin/tables";
        }
        if(name.equals("typography")) {
            return "admin/typography";
        }
        return "admin/index";
    }

}

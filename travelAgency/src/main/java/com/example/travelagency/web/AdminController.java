package com.example.travelagency.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

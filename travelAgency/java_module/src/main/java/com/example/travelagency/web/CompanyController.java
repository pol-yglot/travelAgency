package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "service")
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/businessTrip")
    public String businessTrip() {
        return "service/businessTrip";
    }

    @GetMapping("/mice")
    public String mice() {
        return "service/mice";
    }

    @GetMapping("/inquiry")
    public String inquiry() {
        return "service/inquiry";
    }

    @GetMapping("/company")
    public String company() {
        return "service/company";
    }
}

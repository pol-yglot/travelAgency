package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "service")
public class ServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/services")
    public String services() {
        return "service/services";
    }

    @GetMapping("/mice")
    public String mice() {
        return "service/mice";
    }

    @GetMapping("/inquiry")
    public String inquiry() {
        return "service/inquiry";
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "service/introduce";
    }
}

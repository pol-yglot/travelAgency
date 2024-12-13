package com.travelagency.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    // 홈
    @GetMapping("/")
    public String home() throws Exception{
        return "index";
    }

    // generic
    @GetMapping("/generic")
    public String generic() throws Exception{
        return "generic";
    }

    // elements
    @GetMapping("/elements")
    public String elements() throws Exception{
        return "elements";
    }

}

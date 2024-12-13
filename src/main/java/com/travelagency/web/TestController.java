package com.travelagency.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    // 메인
    @GetMapping("/")
    public String home() throws Exception{
        return "index";
    }
    // 공지사항
    @GetMapping("/noti")
    public String noti() throws Exception{
        return "noti";
    }
    // 비즈니스출장
    @GetMapping("/corpTour")
    public String corpTour() throws Exception{
        return "corpTour";
    }
    // 학회/협회
    @GetMapping("/conference")
    public String conference() throws Exception{
        return "conference";
    }
    // 견적문의
    @GetMapping("/inquire")
    public String inquire() throws Exception{
        return "inquire";
    }
    // 회사소개
    @GetMapping("/introduce")
    public String introduce() throws Exception{
        return "introduce";
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

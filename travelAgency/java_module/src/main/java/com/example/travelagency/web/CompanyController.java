package com.example.travelagency.web;

import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.InquiryVO;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/inquiry")
    public String inquiry(@Valid InquiryVO inq, ServletRequest servletRequest){
        int result = userService.insertInquiry(inq);
        if(result < 1) {
            return "redirect:/error/error";
        }else{
            return "redirect:/common/success";
        }
    }

    @GetMapping("/company")
    public String company() {
        return "service/company";
    }
}

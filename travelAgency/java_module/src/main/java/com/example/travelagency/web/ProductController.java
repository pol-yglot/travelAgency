package com.example.travelagency.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "product")
public class ProductController {

    @GetMapping("/productList")
    public String productList(Model model) {
        return "product/productList";
    }

    @GetMapping("/productDetail")
    public String productDetail(Model model) {
        return "product/productDetail";
    }

}

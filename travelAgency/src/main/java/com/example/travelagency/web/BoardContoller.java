package com.example.travelagency.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "board")
public class BoardContoller {

    @GetMapping("/contact")
    public String contact(Model model) {
        return "board/contact";
    }

    @GetMapping("/boardList")
    public String boardList(Model model) {
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model) {
        return "board/boardDetail";
    }

}

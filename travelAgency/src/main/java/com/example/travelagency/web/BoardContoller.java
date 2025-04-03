package com.example.travelagency.web;

import com.example.travelagency.service.BoardService;
import com.example.travelagency.vo.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "board")
public class BoardContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardContoller.class);

    @Autowired
    private BoardService boardService;

    @GetMapping("/contact")
    public String contact(Model model) {
        return "board/contact";
    }

    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardVO> boardList = boardService.getAllBoard();
        model.addAttribute("boardList", boardList);
        LOGGER.debug("게시글 리스트 {}",boardList.toString());
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model) {
        return "board/boardDetail";
    }

}

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
import org.springframework.web.bind.annotation.RequestParam;
import com.example.travelagency.vo.PageInfo;

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
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        PageInfo<BoardVO> pageInfo = boardService.getAllBoard(page);
        LOGGER.debug("페이지 수  :::::: {}",pageInfo.getPageSize());

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("boardList", pageInfo.getItems());
        model.addAttribute("title", "게시판"); // 제목 추가 (템플릿에서 사용)
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model) {
        return "board/boardDetail";
    }

}

package com.example.travelagency.web;

import com.example.travelagency.service.BoardService;
import com.example.travelagency.vo.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.travelagency.vo.PageInfo;

import java.util.ArrayList;
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

    /**
     * ResponseEntity : HTTP 상태 코드와 함께 응답 데이터를 클라이언트에 반환
     * */
    @GetMapping("/searchBoard")
    public ResponseEntity<List<BoardVO>> searchBoard(@RequestParam("searchType") String searchType,
                                                     @RequestParam("keyword") String keyword) {
        List<BoardVO> boardList = null;
        try {
            switch (searchType) {
                case "content":
                    boardList = boardService.getBoardByContent(keyword);
                    break;
                case "title":
                    boardList = boardService.getBoardByTitle(keyword);
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 잘못된 요청
            }
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            // 로깅
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 오류
        }
    }

    @GetMapping("/boardList")
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        PageInfo<BoardVO> pageInfo = boardService.getAllBoard(page);
        LOGGER.debug("페이지 수  :::::: {}",pageInfo.getPageSize());

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("boardList", pageInfo.getItems());
        /* model.addAttribute("title", "게시판"); // 제목 추가 (템플릿에서 사용) */
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model) {
        return "board/boardDetail";
    }

}

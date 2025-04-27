package com.example.travelagency.web;

import com.example.travelagency.service.BoardService;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.travelagency.vo.PageInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "board")
public class BoardContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardContoller.class);
    private final String boardFilesLocation = "/app/files/board/"; // 실제 파일 저장 경로

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

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

        int startIndex = (page - 1) * pageInfo.getPageSize();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("boardList", pageInfo.getItems());
        model.addAttribute("startIndex", startIndex);  // 다음페이지로 넘어갔을때 시작할 인덱스 + 게시글 인덱스로 순번 계산
        /* model.addAttribute("title", "게시판"); // 제목 추가 (템플릿에서 사용) */
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam("BOARD_ID") int BOARD_ID, HttpSession session, Model model) {
        try {
            Map<String, Object> board = boardService.getBoardById(BOARD_ID);
            // 글작성자 == 로그인사용자
            if(session.getAttribute("user") != null) {
                LOGGER.info("세션 데이터 확인 {}",session.getAttribute("user").toString());
                UserVO user = (UserVO) session.getAttribute("user");
                model.addAttribute("user", user);
            }
            List<CommentVO> commentList = boardService.getCommentListByBoardId(BOARD_ID);
            model.addAttribute("board", board);
            model.addAttribute("commentList", commentList);
        }catch (Exception e) {
            throw new RuntimeException("게시판 상세 데이터를 불러오는 중 오류 발생, boardDetail", e);
        }

        return "board/boardDetail";
    }

    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> serveFile(@PathVariable String filename) throws IOException, IOException {
        Path filePath = Paths.get(boardFilesLocation, filename);
        FileSystemResource resource = new FileSystemResource(filePath);

        if (resource.exists()) {
            String contentType = Files.probeContentType(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

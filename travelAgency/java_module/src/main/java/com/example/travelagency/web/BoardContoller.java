package com.example.travelagency.web;

import com.example.travelagency.service.BoardService;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.travelagency.vo.PageInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /**
     * 고객센터
     * */
    @GetMapping("/contact")
    public String contact(Model model) {
        List<BoardVO> boardList = boardService.selectTop5ViewedPosts();
        int totalBoardCount = boardService.getTotalBoardCount();
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalBoardCount", totalBoardCount);
        return "board/contact";
    }

    /**
     * 게시판
     * @param page, searchType, keyword
     * */
    @GetMapping("/boardList")
    public String boardList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "searchType", required = false) String searchType,
                            @RequestParam(value = "keyword", required = false) String keyword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";

        // 인증된 사용자 정보가 있는지 확인
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String)) { // "anonymousUser" 방지

            // principal 꺼내기
            org.springframework.security.core.userdetails.User userDetails =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            String username = userDetails.getUsername(); // 로그인한 아이디
            UserVO user = userService.getUser(username);
            userId = String.valueOf(user.getUSER_ID());
            model.addAttribute("user", user);

        }

        PageInfo<BoardVO> pageInfo;

        // 검색
        if (keyword != null && !keyword.isEmpty()) {
            pageInfo = boardService.searchBoard(page, searchType, keyword);
        }
        // 본인 게시글
        if(searchType != null && searchType.equals("userId")){
            pageInfo = boardService.searchBoard(page, searchType, userId);
        } else {
            // 전체 조회
            pageInfo = boardService.getAllBoard(page);
        }

        int startIndex = (page - 1) * pageInfo.getPageSize();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("boardList", pageInfo.getItems());
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "board/boardList";
    }

    /**
     * 게시판 상세보기
     * @param BOARD_ID
     * */
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam("BOARD_ID") int BOARD_ID, HttpSession session, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증된 사용자 정보가 있는지 확인
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String)) { // "anonymousUser" 방지

            // principal 꺼내기
            org.springframework.security.core.userdetails.User userDetails =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            String username = userDetails.getUsername(); // 로그인한 아이디
            UserVO user = userService.getUser(username);
            model.addAttribute("user", user);
        }
        try {
            // 조회수 + 1
            boardService.incrementBoardHits(BOARD_ID);

            Map<String, Object> board = boardService.getBoardById(BOARD_ID);
            List<CommentVO> commentList = boardService.getCommentListByBoardId(BOARD_ID);

            model.addAttribute("board", board);
            model.addAttribute("commentList", commentList);

        }catch (Exception e) {
            throw new RuntimeException("게시판 상세 데이터를 불러오는 중 오류 발생, boardDetail", e);
        }

        return "board/boardDetail";
    }

    /**
     * 여행정보 페이지
     * */
    @GetMapping("/travelNews")
    public String travelNews(Model model) {
        return "board/travelNews";
    }

    /**
     * 게시판 댓글달기
     * @param BOARD_ID, COMMENT_WRITER, COMMENT_CONTENT
     * */
    @PostMapping("/addComment")
    public String addComment(@RequestParam int BOARD_ID,@RequestParam String COMMENT_WRITER, @RequestParam String COMMENT_CONTENT) {
        try{
            LOGGER.info(BOARD_ID+":"+COMMENT_WRITER+":"+COMMENT_CONTENT);
            int result = 0;
            result = boardService.addComment(BOARD_ID, COMMENT_WRITER, COMMENT_CONTENT);
            return "redirect:/board/boardDetail?BOARD_ID="+BOARD_ID;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 게시판 파일 불러오기
     * @param filename
     * */
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

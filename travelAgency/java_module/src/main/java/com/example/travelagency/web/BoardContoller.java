package com.example.travelagency.web;

import com.example.travelagency.service.BoardService;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.travelagency.vo.PageInfo;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    private final String boardFilesLocation = "/app/files/board/"; // 실제 파일 저장 경로

    /**
     * 고객센터
     * */
    @GetMapping("/contact")
    public String contact(Model model, HttpSession session) {
        List<BoardVO> boardList = boardService.selectTop5ViewedPosts();
        int totalBoardCount = boardService.getTotalBoardCount();
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalBoardCount", totalBoardCount);
        return "board/contact";
    }

    /**
     * 파이썬 구구단 모델
     * */
    @GetMapping("/predict")
    public String predict() {
        return "test";
    }

    // python-django api test
    @PostMapping("/predict")
    public String predict(@RequestParam double x, Model model) throws Exception {
        String djangoUrl = "http://localhost:8000/api/predict/";

        JSONObject json = new JSONObject();
        json.put("x", x);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(djangoUrl, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());
        model.addAttribute("result", result.getDouble("prediction"));;

        return "/test";
    }

    /**
     * 게시글 리스트 조회
     * @param page, searchType, keyword
     * */
    @GetMapping("/boardList")
    public Object boardList(Model model, HttpServletRequest request,
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

        pageInfo = boardService.getAllBoard(page);

        // 검색
        if (keyword != null && !keyword.isEmpty()) {
            pageInfo = boardService.searchBoard(page, searchType, keyword);
        }
        // 본인 게시글
        if(searchType != null && searchType.equals("userId")){
            pageInfo = boardService.searchBoard(page, searchType, userId);
        }

        int startIndex = (page - 1) * pageInfo.getPageSize();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("boardList", pageInfo.getItems());

        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if(isAjax){
            // return ResponseEntity.ok(pageInfo.getItems()); // 페이징 안된 데이터
            return ResponseEntity.ok(pageInfo);
        }else{
            return "board/boardList";
        }
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

    @GetMapping("/writeBoard")
    public String writeBoard(Model model) {
        return "board/writeBoard";
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

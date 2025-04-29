package com.example.travelagency.service.impl;

import com.example.travelagency.mapper.BoardMapper;
import com.example.travelagency.service.BoardService;
import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import com.example.travelagency.vo.PageInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper ;

    private static final int PAGE_SIZE = 10; // 한 페이지에 보여줄 게시글 수

    @Override
    public PageInfo<BoardVO> getAllBoard(int page) {
        int totalItems = boardMapper.getTotalBoardCount();
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
        int offset = (page - 1) * PAGE_SIZE;
        List<BoardVO> boardList = boardMapper.getAllBoardPaged(offset, PAGE_SIZE);

        return new PageInfo<>(boardList, page, totalPages, PAGE_SIZE, totalItems);
    }

    @Override
    public Map<String, Object> getBoardById(int boardId) {
        return boardMapper.getBoardById(boardId);
    }

    @Override
    public List<CommentVO> getCommentListByBoardId(int boardId) {
        return boardMapper.getCommentListByBoardId(boardId);
    }

    @Override
    public int addComment(int boardId, String commentWriter, String commentContent) {
        return boardMapper.addComment(boardId, commentWriter, commentContent);
    }

    @Override
    public PageInfo<BoardVO> searchBoard(int page, String searchType, String keyword) {
        int pageSize = PAGE_SIZE; // 한 페이지에 보여줄 게시글 수
        int offset = (page - 1) * pageSize; // 가져올 시작 인덱스

        List<BoardVO> boardList;
        int totalItems;

        // 검색 타입에 따라 분기
        switch (searchType) {
            case "title":
                boardList = boardMapper.getBoardByTitlePaged(keyword, offset, pageSize);
                totalItems = boardMapper.getTotalBoardCountByTitle(keyword);
                break;
            case "content":
                boardList = boardMapper.getBoardByContentPaged(keyword, offset, pageSize);
                totalItems = boardMapper.getTotalBoardCountByContent(keyword);
                break;
            case "userId":
                boardList = boardMapper.getBoardByUserAccount(keyword, offset, pageSize);
                totalItems = boardMapper.getTotalBoardCountByUserAccount(keyword);
                break;
            default:
                throw new IllegalArgumentException("잘못된 검색 타입입니다: " + searchType);
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        return new PageInfo<>(boardList, page, totalPages, pageSize, totalItems);
    }

    @Override
    public List<BoardVO> selectTop5ViewedPosts() {
        return boardMapper.selectTop5ViewedPosts();
    }

    @Override
    public int getTotalBoardCount() {
        return boardMapper.getTotalBoardCount();
    }

    @Override
    public void incrementBoardHits(int boardId) {
        boardMapper.incrementBoardHits(boardId);
    }

    /**
     * 드라이버 체킹
     * */
    @PostConstruct
    public void checkJdbcDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
}

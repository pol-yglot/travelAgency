package com.example.travelagency.service.impl;

import com.example.travelagency.mapper.BoardMapper;
import com.example.travelagency.service.BoardService;
import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.PageInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BoardVO> getBoardByTitle(String keyword) {
        return boardMapper.getBoardListByTitle(keyword);
    }

    @Override
    public List<BoardVO> getBoardByContent(String keyword) {
        return boardMapper.getBoardListByContent(keyword);
    }

    @Override
    public BoardVO getBoardById(int boardId) {
        return boardMapper.getBoardById(boardId);
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

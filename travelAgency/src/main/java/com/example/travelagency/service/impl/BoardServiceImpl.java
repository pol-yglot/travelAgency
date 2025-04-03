package com.example.travelagency.service.impl;

import com.example.travelagency.mapper.BoardMapper;
import com.example.travelagency.service.BoardService;
import com.example.travelagency.vo.BoardVO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper ;

    @Override
    public List<BoardVO> getAllBoard() {
        return boardMapper.getAllBoard();
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

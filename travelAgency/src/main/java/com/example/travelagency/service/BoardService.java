package com.example.travelagency.service;

import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.PageInfo;

import java.util.List;

public interface BoardService {
    PageInfo<BoardVO> getAllBoard(int page);
    List<BoardVO> getBoardByTitle(String keyword);
    List<BoardVO> getBoardByContent(String keyword);
}

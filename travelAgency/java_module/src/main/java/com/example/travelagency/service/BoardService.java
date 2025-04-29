package com.example.travelagency.service;

import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import com.example.travelagency.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface BoardService {
    PageInfo<BoardVO> getAllBoard(int page);
    Map<String, Object> getBoardById(int boardId);
    List<CommentVO> getCommentListByBoardId(int boardId);
    int addComment(int boardId, String commentWriter, String commentContent);
    PageInfo<BoardVO> searchBoard(int page, String searchType, String keyword);
    List<BoardVO> selectTop5ViewedPosts();
    int getTotalBoardCount();
    void incrementBoardHits(int boardId);
}

package com.example.travelagency.service;


import com.example.travelagency.vo.CommentVO;

import java.util.List;

public interface CommentService {
    List<CommentVO> getCommentsByBoardId(Long boardId);
    CommentVO getCommentById(Long commentId);
    boolean addComment(CommentVO commentVO);
    boolean updateComment(CommentVO commentVO);
    boolean deleteComment(Long commentId);
    int getCommentCountByBoardId(Long boardId);
}

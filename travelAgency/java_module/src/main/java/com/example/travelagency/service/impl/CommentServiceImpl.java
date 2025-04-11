package com.example.travelagency.service.impl;

import com.example.travelagency.mapper.CommentMapper;
import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.service.CommentService;
import com.example.travelagency.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentVO> getCommentsByBoardId(Long boardId) {
        return List.of();
    }

    @Override
    public CommentVO getCommentById(Long commentId) {
        return null;
    }

    @Override
    public boolean addComment(CommentVO commentVO) {
        return false;
    }

    @Override
    public boolean updateComment(CommentVO commentVO) {
        return false;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        return false;
    }

    @Override
    public int getCommentCountByBoardId(Long boardId) {
        return 0;
    }
}
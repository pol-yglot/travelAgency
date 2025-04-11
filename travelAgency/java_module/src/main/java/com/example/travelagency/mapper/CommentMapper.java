package com.example.travelagency.mapper;

import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentVO> getCommentsByBoardId(Long boardId);
    CommentVO getCommentById(Long commentId);
    boolean addComment(CommentVO commentVO);
    boolean updateComment(CommentVO commentVO);
    boolean deleteComment(Long commentId);
    int getCommentCountByBoardId(Long boardId);
}

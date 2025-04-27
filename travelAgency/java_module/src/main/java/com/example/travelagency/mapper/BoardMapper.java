package com.example.travelagency.mapper;

import com.example.travelagency.vo.BoardVO;
import com.example.travelagency.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<BoardVO> getAllBoardPaged(@Param("offset") int offset, @Param("limit") int limit);
    int getTotalBoardCount();
    List<BoardVO> getBoardListByTitle(String keyword);
    List<BoardVO> getBoardListByContent(String keyword);
    Map<String, Object> getBoardById(int boardId);
    List<CommentVO> getCommentListByBoardId(int boardId);
}

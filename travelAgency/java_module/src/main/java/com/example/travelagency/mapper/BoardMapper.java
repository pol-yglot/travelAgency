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
    Map<String, Object> getBoardById(int boardId);
    List<CommentVO> getCommentListByBoardId(int boardId);
    int addComment(int boardId, String commentWriter, String commentContent);
    List<BoardVO> getBoardByTitlePaged(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);
    List<BoardVO> getBoardByContentPaged(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);
    int getTotalBoardCountByTitle(@Param("keyword") String keyword);
    int getTotalBoardCountByContent(@Param("keyword") String keyword);

}

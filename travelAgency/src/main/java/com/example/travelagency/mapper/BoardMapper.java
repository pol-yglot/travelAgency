package com.example.travelagency.mapper;

import com.example.travelagency.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> getAllBoardPaged(@Param("offset") int offset, @Param("limit") int limit);
    int getTotalBoardCount();
}

package com.example.travelagency.mapper;

import com.example.travelagency.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // mvn dependency:tree
    List<UserVO> getAllUser();
}


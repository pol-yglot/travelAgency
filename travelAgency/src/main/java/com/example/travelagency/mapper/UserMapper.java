package com.example.travelagency.mapper;

import com.example.travelagency.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVO> getAllUser();
    UserVO getUser(@Param("inputId") String inputId);
    int isUserExist(@Param("inputId") String inputId);
}


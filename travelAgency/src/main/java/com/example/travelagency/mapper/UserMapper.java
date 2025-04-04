package com.example.travelagency.mapper;

import com.example.travelagency.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    // mvn dependency:tree
    List<UserVO> getAllUser();
    UserVO getUser(String inputId);
}


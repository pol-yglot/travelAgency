package com.example.travelagency.service;

import com.example.travelagency.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAllUser();
    UserVO getUser(String inputId);
}

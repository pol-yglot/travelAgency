package com.example.travelagency.service.impl;

import com.example.travelagency.vo.UserVO;
import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> getAllUser() {
        try {
            return userMapper.getAllUser();
        } catch (Exception e) {
            throw new RuntimeException("사용자 목록을 불러오는 중 오류 발생", e);
        }
    }

    @Override
    public UserVO getUser(String inputId) {
        try {
            return userMapper.getUser(inputId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보를 불러오는 중 오류 발생", e);
        }
    }

}

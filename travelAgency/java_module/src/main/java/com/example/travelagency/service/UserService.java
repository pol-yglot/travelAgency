package com.example.travelagency.service;

import com.example.travelagency.vo.InquiryVO;
import com.example.travelagency.vo.UserDetailVO;
import com.example.travelagency.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAllUser();
    UserVO getUser(String inputId);
    UserVO getUserById(int userId);
    boolean isUserExist(String inputId);
    UserVO login(String username, String inputPassword);
    UserDetailVO getUserDetail(int userId);
    void updateProfileImage(String profileImageUrl, int userId);
    int updateUser(UserVO user);
    int insertInquiry(InquiryVO inquiry);
    List<InquiryVO> getInquiryList(int inputId);
    InquiryVO getInquiry(int inquiryId);
    int updateUserDetail(UserDetailVO userDetail);
}

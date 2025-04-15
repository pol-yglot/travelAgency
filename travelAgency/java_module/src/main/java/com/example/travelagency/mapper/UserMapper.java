package com.example.travelagency.mapper;

import com.example.travelagency.vo.InquiryVO;
import com.example.travelagency.vo.UserDetailVO;
import com.example.travelagency.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVO> getAllUser();
    UserVO getUser(@Param("inputId") String inputId);
    int isUserExist(@Param("inputId") String inputId);
    UserDetailVO getUserDetail(int userId);
    UserVO getUserById(int userId);
    void updateProfileImage(String profileImageUrl, int userId);
    int updateUser(UserVO user);
    int insertInquiry(InquiryVO inquiry);
    List<InquiryVO> getInquiryList(int inputId);
    InquiryVO getInquiry(int inquiryId);
    int updateUserDetail(UserDetailVO userDetail);
}


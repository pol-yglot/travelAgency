package com.example.travelagency.service.impl;

import com.example.travelagency.vo.InquiryVO;
import com.example.travelagency.vo.UserDetailVO;
import com.example.travelagency.vo.UserVO;
import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            throw new RuntimeException("사용자 목록을 불러오는 중 오류 발생, getAllUser", e);
        }
    }

    @Override
    public UserVO getUser(String inputId) {
        try {
            return userMapper.getUser(inputId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보를 불러오는 중 오류 발생, getUser", e);
        }
    }

    @Override
    public UserVO getUserById(int userId) {
        try {
            return userMapper.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보를 불러오는 중 오류 발생, getUserById", e);
        }
    }

    @Override
    public UserDetailVO getUserDetail(int userId) {
        try {
            return userMapper.getUserDetail(userId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 상세 정보를 불러오는 중 오류 발생, getUserDetail", e);
        }
    }

    @Override
    public void updateProfileImage(String profileImageUrl, int userId) {
        try {
            userMapper.updateProfileImage(profileImageUrl, userId);
        } catch (Exception e) {
            throw new RuntimeException("프로필 이미지 업로드 중 오류 발생, updateProfileImage", e);
        }
    }

    @Override
    public int updateUser(UserVO user) {
        try {
            return userMapper.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException("고객정보 수정 중 오류 발생, updateUser", e);
        }
    }

    @Override
    public int insertInquiry(InquiryVO inquiry) {
        try {
            return userMapper.insertInquiry(inquiry);
        } catch (Exception e) {
            throw new RuntimeException("견적문의 저장 중 오류 발생, updateUser", e);
        }
    }

    @Override
    public List<InquiryVO> getInquiryList(int inputId) {
        return userMapper.getInquiryList(inputId);
    }

    @Override
    public InquiryVO getInquiry(int inquiryId) {
        return userMapper.getInquiry(inquiryId);
    }

    @Override
    public int updateUserDetail(UserDetailVO userDetail) {
        try {
            return userMapper.updateUserDetail(userDetail);
        } catch (Exception e) {
            throw new RuntimeException("고객정보 수정 중 오류 발생, updateUserDetail", e);
        }
    }

    @Override
    public int userSignout(String useraccount) {
        return userMapper.userSignout(useraccount);
    }

    @Override
    public int insertUser(UserVO user) {

        // 비밀번호 인코딩 처리
        String password = user.getUSER_PASSWORD();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try{
            user.setUSER_PASSWORD(encoder.encode(password));
        }catch (Exception e){
            e.printStackTrace();
        }

        return userMapper.insertUser(user);
    }

    @Override
    public int insertUserDtl(UserDetailVO userDtl) {
        return userMapper.insertUserDtl(userDtl);
    }

    @Override
    public boolean isUserExist(String inputId) {
        try {
            int isUserExistYn = userMapper.isUserExist(inputId);

            if (isUserExistYn > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보를 불러오는 중 오류 발생, isUserExist", e);
        }
    }

    @Override
    public UserVO login(String inputId, String inputPassword) {
        try {
            UserVO user = userMapper.getUser(inputId);

            // 아이디 일치 여부 확인
            if (user != null && user.getUSER_ACCOUNT().equals(inputId)) {
                if (user != null && user.getUSER_PASSWORD().equals(inputPassword)) {
                    return user; // 로그인 성공
                }
                return null; //로그인 실패
            } else {
                return null; // 로그인 실패
            }
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보를 불러오는 중 오류 발생, login", e);
        }
    }
}
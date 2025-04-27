package com.example.travelagency.web;

import com.example.travelagency.service.FileService;
import com.example.travelagency.service.UserService;
import com.example.travelagency.vo.InquiryVO;
import com.example.travelagency.vo.UserDetailVO;
import com.example.travelagency.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/getInquiry")
    public String getInquiry(@RequestParam("inquiryId") int inquiryId, Model model) {
        InquiryVO inquiry = userService.getInquiry(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "/user/inquiryDetail";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        // 로그인 시 세션에 올린 사용자 정보를 재사용
        if (session.getAttribute("user") != null) {

            UserVO user = (UserVO) session.getAttribute("user");
            UserDetailVO userDetail = userService.getUserDetail(user.getUSER_ID());
            List<InquiryVO> inqList = userService.getInquiryList(user.getUSER_ID());

            model.addAttribute("user", user);
            model.addAttribute("userDetail", userDetail);
            model.addAttribute("inqList", inqList);
        } else {
            // 미로그인시 로그인 페이지로 이동
            return "user/login";
        }
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/updateProfile")
    public String updateProfile(HttpSession session, Model model) {
        // 로그인 시 세션에 올린 사용자 정보를 재사용
        if (session.getAttribute("user") != null) {
            UserVO user = (UserVO) session.getAttribute("user");
            // USER_ID로 상세정보 조회
            UserDetailVO userDetail = userService.getUserDetail(user.getUSER_ID());
            LOGGER.debug("userDetail = " + userDetail);
            model.addAttribute("user", user);
            model.addAttribute("userDetail", userDetail);
        } else {
            // 미로그인시 로그인 페이지로 이동
            return "user/login";
        }
        return "user/updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfileProcess(
            @RequestParam("USER_NAME") String USER_NAME,
            @RequestParam("USER_PHONE") String USER_PHONE,
            @RequestParam("USER_EMAIL") String USER_EMAIL,
            @RequestParam("USER_PASSWORD") String USER_PASSWORD,
            @RequestParam("USER_TYPE") String USER_TYPE,
            @RequestParam(value = "USER_ADDRESS", required = false) String USER_ADDRESS,
            @RequestParam(value = "USER_PREFERENCE", required = false) String USER_PREFERENCE,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        if (session.getAttribute("user") != null) {
            // 수행여부 체크 플래그
            int result = 0;
            // 1. user 정보 업데이트
            UserVO user = (UserVO) session.getAttribute("user");
            user.setUSER_NAME(USER_NAME);
            user.setUSER_PHONE(USER_PHONE);
            user.setUSER_EMAIL(USER_EMAIL);
            user.setUSER_TYPE(USER_TYPE);
            //user.setUSER_PASSWORD(USER_PASSWORD);

            // ✅ 비밀번호 암호화 후 저장
            if (USER_PASSWORD != null && !USER_PASSWORD.isEmpty()) {
                String encryptedPassword = passwordEncoder.encode(USER_PASSWORD);
                user.setUSER_PASSWORD(encryptedPassword);
            }
            result = userService.updateUser(user);

            if(result < 1){
                redirectAttributes.addFlashAttribute("mesage", "고객정보수정을 실패했습니다. 다시 시도해주세요.");
                return "redirect:/user/profile";
            }

            // 2. userDetail 정보 업데이트
            UserDetailVO userDetail = userService.getUserDetail(user.getUSER_ID());
            userDetail.setUSER_ID(String.valueOf(user.getUSER_ID()));
            userDetail.setUSER_ADDRESS(USER_ADDRESS);
            userDetail.setUSER_PREFERENCE(USER_PREFERENCE);
            result = userService.updateUserDetail(userDetail);

            if(result < 1){
                redirectAttributes.addFlashAttribute("mesage", "고객정보수정에 실패했습니다. 다시 시도해주세요.");
                return "redirect:/user/profile";
            }

        } else {
            // 미로그인시 로그인 페이지로 이동
            return "user/login";
        }
        return "redirect:/user/profile";
    }

    @PostMapping("/updateProfileImage")
    public String updateProfileImage(@RequestParam("profileImage") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("user")!= null && !file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String uploadDir = fileService.getProfileImageDirectory();
            UserVO user = (UserVO) session.getAttribute("user");
            int userId = user.getUSER_ID();

            try {
                fileService.saveFile(file, uploadDir, filename);
                redirectAttributes.addFlashAttribute("message", "프로필 이미지 업로드 성공: " + filename);

                String profileImageUrl = "/user/" + filename;
                userService.updateProfileImage(profileImageUrl, userId);

            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("message", "프로필 이미지 업로드에 실패했습니다. 다시 시도해주세요.");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "업로드할 파일을 선택해주세요.");
        }
        return "redirect:/user/profile";
    }
}
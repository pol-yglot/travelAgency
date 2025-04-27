package com.example.travelagency.service;

import com.example.travelagency.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = userService.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        String role = "USER"; // 기본값
        if (user.getUSER_TYPE() != null && user.getUSER_TYPE().equalsIgnoreCase("admin")) {
            role = "ADMIN"; // 만약 사용자 타입이 "admin"이면 ADMIN 권한 부여
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUSER_ACCOUNT())
                .password(user.getUSER_PASSWORD())
                .roles(role)
                .build();
    }

}
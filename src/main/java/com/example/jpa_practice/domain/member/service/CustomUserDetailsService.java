package com.example.jpa_practice.domain.member.service;

import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.global.exception.CustomException;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 검증할 User 조회
        User user = userRepository.findByEmailAndDeletedAtIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}


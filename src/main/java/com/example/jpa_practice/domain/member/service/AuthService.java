package com.example.jpa_practice.domain.member.service;

import com.example.jpa_practice.domain.member.dto.LoginRequestDto;
import com.example.jpa_practice.domain.member.dto.SignupRequestDto;
import com.example.jpa_practice.domain.member.dto.SignupResponseDto;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.global.exception.CustomException;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        // 이메일 중복 확인
        if (userRepository.existsByEmailAndDeletedAtIsNull(requestDto.getEmail())) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "이미 존재하는 이메일입니다."
            );
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // User 엔티티 생성
        User user = User.builder()
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .role(com.example.jpa_practice.domain.member.enums.Role.ROLE_USER)  // 기본 역할 설정
                .gender(requestDto.getGender())
                .birth(requestDto.getBirth())
                .address(requestDto.getAddress())
                .phoneNumber(requestDto.getPhoneNumber())
                .point(0)  // 기본 포인트 0
                .socialType(com.example.jpa_practice.domain.member.enums.SocialType.KAKAO)  // 기본값
                .updatedAt(LocalDateTime.now())
                .build();

        // DB에 저장
        User savedUser = userRepository.save(user);

        return SignupResponseDto.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .message("회원가입이 완료되었습니다.")
                .build();
    }

    public void login(LoginRequestDto requestDto) {
        // UserDetailsService에서 인증 처리
        // 실제 로그인 로직은 Spring Security가 처리
    }

    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "사용자를 찾을 수 없습니다: " + email
                ));
        userRepository.delete(user);
    }
}


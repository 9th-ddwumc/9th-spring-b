package com.example.jpa_practice.domain.member.controller;

import com.example.jpa_practice.domain.member.dto.LoginRequestDto;
import com.example.jpa_practice.domain.member.dto.SignupRequestDto;
import com.example.jpa_practice.domain.member.dto.SignupResponseDto;
import com.example.jpa_practice.domain.member.service.AuthService;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralSuccessCode;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    /**
     * 회원가입 API
     * POST /api/auth/signup
     */
    @PostMapping("/signup")
    public ApiResponse<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        SignupResponseDto response = authService.signup(requestDto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    /**
     * 로그인 API
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpSession session) {
        // Spring Security 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );

        // SecurityContext에 인증 정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 세션에 인증 정보 저장
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "로그인 성공");
    }

    /**
     * 로그아웃 API
     * POST /api/auth/logout
     */
    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        // SecurityContext 초기화
        SecurityContextHolder.clearContext();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "로그아웃 성공");
    }

    /**
     * 테스트용: 사용자 삭제 API
     * DELETE /api/auth/delete/{email}
     * 주의: 테스트용이므로 프로덕션에서는 제거하거나 보안 강화 필요
     */
    @DeleteMapping("/delete/{email}")
    public ApiResponse<String> deleteUser(@PathVariable String email) {
        authService.deleteUser(email);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "사용자가 삭제되었습니다: " + email);
    }
}


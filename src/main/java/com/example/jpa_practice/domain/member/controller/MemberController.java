package com.example.jpa_practice.domain.member.controller;

import com.example.jpa_practice.domain.member.dto.MemberReqDTO;
import com.example.jpa_practice.domain.member.dto.MemberResDTO;
import com.example.jpa_practice.domain.member.exception.code.MemberSuccessCode;
import com.example.jpa_practice.domain.member.service.MemberCommandService;
import com.example.jpa_practice.domain.member.service.MemberQueryService;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 관리", description = "회원가입 및 로그인 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하여 JWT Access Token을 발급받습니다.")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}


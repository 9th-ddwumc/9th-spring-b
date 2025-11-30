package com.example.practice_spring.domain.member.controller;

import com.example.practice_spring.domain.member.dto.req.MemberReqDTO;
import com.example.practice_spring.domain.member.dto.res.MemberResDTO;
import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.member.service.MemberCommandService;
import com.example.practice_spring.domain.member.service.MemberQueryService;
import com.example.practice_spring.domain.member.service.MemberService;
import com.example.practice_spring.global.apiPayload.ApiResponse;
import com.example.practice_spring.global.apiPayload.code.BaseSuccessCode;
import com.example.practice_spring.global.apiPayload.code.MemberSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}

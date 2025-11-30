package com.workbook.umc9th1.member.controller;

import com.workbook.umc9th1.global.apiPayload.ApiResponse;
import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;
import com.workbook.umc9th1.member.exception.code.MemberSuccessCode;
import com.workbook.umc9th1.member.service.command.MemberCommandServiceImpl;
import com.workbook.umc9th1.member.service.query.MemberQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandServiceImpl memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDto.JoinDto> signUp(
            @RequestBody @Valid MemberReqDto.JoinDto dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDto.LoginDto> login(
            @RequestBody @Valid MemberReqDto.LoginDto dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}

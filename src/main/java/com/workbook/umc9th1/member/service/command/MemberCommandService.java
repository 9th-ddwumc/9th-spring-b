package com.workbook.umc9th1.member.service.command;

import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;

public interface MemberCommandService {
    // 회원가입
    MemberResDto.JoinDto signup(
            MemberReqDto.JoinDto dto
    );
}

package com.workbook.umc9th1.member.dto;

public record MyPageDto(
        Long memberId,
        String nickname,
        String email,
        String phoneInfo,
        Long totalPoint
) {
}

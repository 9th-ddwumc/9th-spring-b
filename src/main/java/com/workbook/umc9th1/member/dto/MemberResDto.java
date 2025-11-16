package com.workbook.umc9th1.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDto {

    @Builder
    public record JoinDto(
            Long memberId,
            LocalDateTime createdAt
    ){}
}

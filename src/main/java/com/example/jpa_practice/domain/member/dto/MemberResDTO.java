package com.example.jpa_practice.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MemberResDTO {

    public record JoinDTO(
            Long memberId,
            String email,
            String name,
            String message
    ) {}

    // 로그인
    @Schema(description = "로그인 응답 DTO")
    public record LoginDTO(
            @Schema(description = "회원 ID", example = "1")
            Long memberId,
            @Schema(description = "JWT Access Token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            String accessToken
    ) {}
}


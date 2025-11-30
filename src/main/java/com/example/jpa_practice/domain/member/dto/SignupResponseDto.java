package com.example.jpa_practice.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignupResponseDto {
    private Long userId;
    private String email;
    private String nickname;
    private String message;
}


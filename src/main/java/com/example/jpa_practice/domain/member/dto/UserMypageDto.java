package com.example.jpa_practice.domain.member.dto;

public record UserMypageDto(
        String nickname,
        String email,
        String phoneNumber,
        Integer point
) {}

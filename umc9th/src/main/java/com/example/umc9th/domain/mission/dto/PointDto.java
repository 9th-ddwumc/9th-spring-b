package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointDto {
    private Long memberId;
    private String nickname;
    private String email;
    private String phone;
    private Long amount;
}

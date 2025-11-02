package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionMemberDto {
    private Long memberId;
    private String marketName;
    private Long pointAmount;
    private Long missionId;
    private Integer missionPoint;
    private java.time.LocalDateTime missionDeadline;
    private Long marketId;
    private String category;
    private Boolean complete;
}


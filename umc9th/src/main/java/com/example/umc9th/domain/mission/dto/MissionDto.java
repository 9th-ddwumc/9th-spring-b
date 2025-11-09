package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionDto {
    private String locationName;
    private Long missionMemberId;
    private Boolean complete;
    private Integer point;
    private java.time.LocalDateTime deadline;
    private Long marketId;
    private String marketName;
}

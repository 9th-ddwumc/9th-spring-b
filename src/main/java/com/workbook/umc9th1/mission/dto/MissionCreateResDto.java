package com.workbook.umc9th1.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionCreateResDto {
    private Long missionId;
    private Long storeId;
    private String content;
    private Integer rewardPoint;
    private String endDay;
}

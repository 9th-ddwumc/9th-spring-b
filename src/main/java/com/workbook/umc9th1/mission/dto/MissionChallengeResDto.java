package com.workbook.umc9th1.mission.dto;

import com.workbook.umc9th1.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionChallengeResDto {
    private Long memberMissionId;
    private Long missionId;
    private MissionStatus status;
}

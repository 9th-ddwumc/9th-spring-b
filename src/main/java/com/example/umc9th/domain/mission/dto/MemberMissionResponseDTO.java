package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResponseDTO {

    @Builder
    public record ChallengeMissionDTO(
            Long missionId,
            String storeName,
            MissionStatus status,
            String content,
            LocalDate deadline,
            Integer rewardPoint
    ) {}

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionListDTO {
        private List<ChallengeMissionDTO> missionList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }
}

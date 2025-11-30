package com.workbook.umc9th1.mission.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MissionResDto {

    @Builder
    public record MissionListItem(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String content,
            Integer rewardPoint,
            LocalDateTime endDay,
            String status,
            LocalDateTime requestedAt,
            LocalDateTime completedAt
    ) {}

    @Builder
    public record MissionListResponse(
            List<MissionListItem> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}

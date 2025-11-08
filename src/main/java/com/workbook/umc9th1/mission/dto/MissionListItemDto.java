package com.workbook.umc9th1.mission.dto;

import java.time.LocalDateTime;

public record MissionListItemDto(
        Long memberMissionId,
        Long missionId,
        String storeName,
        String content,
        Integer rewardPoint,
        LocalDateTime endDay,
        String status,
        LocalDateTime requestedAt,
        LocalDateTime completedAt
) {
}

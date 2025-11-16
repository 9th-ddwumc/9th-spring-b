package com.workbook.umc9th1.mission.dto;

import com.workbook.umc9th1.food.domain.FoodType;
import com.workbook.umc9th1.store.enums.Address;

import java.time.LocalDateTime;

public record MissionHomeItemDto(
        Long missionId,
        String storeName,
        Address address,
        FoodType foodCategory,
        String content,
        Integer rewardPoint,
        LocalDateTime endDay,
        String missionStatus,
        Long areaCompletedCount,
        String cursorValue
) {
}

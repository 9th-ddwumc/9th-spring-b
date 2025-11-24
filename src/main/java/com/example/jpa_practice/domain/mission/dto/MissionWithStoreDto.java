package com.example.jpa_practice.domain.mission.dto;

import java.time.LocalDate;

public record MissionWithStoreDto(
        Long missionId,
        String conditional,
        Integer score,
        LocalDate missionDeadline,
        String storeName,
        String category
) {}

package com.example.jpa_practice.domain.mission.dto;

import java.time.LocalDate;

public record HomeMissionDto(
        Long missionId,
        String conditional,
        Integer score,
        String storeName,
        String category,
        String locationName,
        LocalDate missionDeadline
) {}

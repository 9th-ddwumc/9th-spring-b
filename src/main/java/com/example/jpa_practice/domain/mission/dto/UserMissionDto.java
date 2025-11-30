package com.example.jpa_practice.domain.mission.dto;

public record UserMissionDto(
        Long missionId,
        String conditional,
        Integer score,
        String storeName,
        Boolean missionSuccess
) {}
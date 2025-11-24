package com.example.jpa_practice.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class UserMissionResDTO {

    private UserMissionResDTO() {
    }

    @Builder
    public record UserMissionPreviewListDTO(
            List<UserMissionPreviewDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record UserMissionPreviewDTO(
            Long missionId,
            String conditional,
            Integer score,
            String storeName,
            Boolean missionSuccess
    ) {
    }
}


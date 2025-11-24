package com.example.jpa_practice.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    private MissionResDTO() {
    }

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String conditional,
            Integer score,
            LocalDate missionDeadline
    ) {
    }
}


package com.example.jpa_practice.domain.mission.converter;

import com.example.jpa_practice.domain.mission.dto.MissionResDTO;
import com.example.jpa_practice.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;

public final class MissionConverter {

    private MissionConverter() {
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> page) {
        List<MissionResDTO.MissionPreviewDTO> missions = page.getContent()
                .stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionPreviewListDTO.builder()
                .missions(missions)
                .listSize(missions.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getMissionId())
                .conditional(mission.getConditional())
                .score(mission.getScore())
                .missionDeadline(mission.getMissionDeadline())
                .build();
    }
}


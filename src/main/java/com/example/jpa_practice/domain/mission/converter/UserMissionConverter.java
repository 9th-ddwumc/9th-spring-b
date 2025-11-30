package com.example.jpa_practice.domain.mission.converter;

import com.example.jpa_practice.domain.mission.dto.UserMissionDto;
import com.example.jpa_practice.domain.mission.dto.UserMissionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public final class UserMissionConverter {

    private UserMissionConverter() {
    }

    public static UserMissionResDTO.UserMissionPreviewListDTO toPreviewList(Page<UserMissionDto> page) {
        List<UserMissionResDTO.UserMissionPreviewDTO> missions = page.getContent()
                .stream()
                .map(UserMissionConverter::toPreview)
                .toList();

        return UserMissionResDTO.UserMissionPreviewListDTO.builder()
                .missions(missions)
                .listSize(missions.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    private static UserMissionResDTO.UserMissionPreviewDTO toPreview(UserMissionDto dto) {
        return UserMissionResDTO.UserMissionPreviewDTO.builder()
                .missionId(dto.missionId())
                .conditional(dto.conditional())
                .score(dto.score())
                .storeName(dto.storeName())
                .missionSuccess(dto.missionSuccess())
                .build();
    }
}


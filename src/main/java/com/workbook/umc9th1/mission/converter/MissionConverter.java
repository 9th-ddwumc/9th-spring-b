package com.workbook.umc9th1.mission.converter;

import com.workbook.umc9th1.mission.dto.MissionListItemDto;
import com.workbook.umc9th1.mission.dto.MissionResDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {


    public static MissionResDto.MissionListItem toMissionListItemDto(MissionListItemDto dto) {
        return MissionResDto.MissionListItem.builder()
                .memberMissionId(dto.memberMissionId())
                .missionId(dto.missionId())
                .storeName(dto.storeName())
                .content(dto.content())
                .rewardPoint(dto.rewardPoint())
                .endDay(dto.endDay())
                .status(dto.status())
                .requestedAt(dto.requestedAt())
                .completedAt(dto.completedAt())
                .build();
    }

    public static MissionResDto.MissionListResponse toMissionListResponse(Page<MissionListItemDto> page) {

        List<MissionResDto.MissionListItem> list = page.getContent().stream()
                .map(MissionConverter::toMissionListItemDto)
                .toList();

        return MissionResDto.MissionListResponse.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}

package com.example.practice_spring.domain.mission.converter;

import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {
    public static MissionResDto toDto(Mission mission) {
        return new MissionResDto(
                mission.getMissionId(),
                mission.getConditional(),
                mission.getPoint(),
                mission.getDeadline()
        );
    }

    public static List<MissionResDto> toDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toDto)
                .toList();
    }
}

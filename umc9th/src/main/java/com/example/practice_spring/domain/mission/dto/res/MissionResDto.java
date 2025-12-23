package com.example.practice_spring.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MissionResDto {
    private Long missionId;
    private String conditional;
    private Integer point;
    private LocalDate deadline;
}

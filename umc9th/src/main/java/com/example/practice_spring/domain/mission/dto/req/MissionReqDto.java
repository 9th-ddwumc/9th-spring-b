package com.example.practice_spring.domain.mission.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MissionReqDto {
    private String condition;
    private int point;
    private LocalDate deadline;
}

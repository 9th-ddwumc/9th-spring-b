package com.workbook.umc9th1.mission.controller;

import com.workbook.umc9th1.mission.dto.MissionCreateReqDto;
import com.workbook.umc9th1.mission.dto.MissionCreateResDto;
import com.workbook.umc9th1.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionService missionService;

    @PostMapping
    public MissionCreateResDto createMission(@RequestBody MissionCreateReqDto request) {
        return missionService.createMission(request);
    }
}

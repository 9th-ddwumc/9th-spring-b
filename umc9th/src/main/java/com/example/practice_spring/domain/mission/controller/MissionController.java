package com.example.practice_spring.domain.mission.controller;

import com.example.practice_spring.domain.mission.dto.req.MissionReqDto;
import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 특정 가게에 미션 추가
    @PostMapping("/{storeId}/missions")
    public MissionResDto addMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDto reqDto
    ) {
        return missionService.addMission(storeId, reqDto);
    }
}

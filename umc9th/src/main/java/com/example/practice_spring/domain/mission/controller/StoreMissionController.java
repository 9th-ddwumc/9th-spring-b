package com.example.practice_spring.domain.mission.controller;

import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreMissionController {

    private final MissionService missionService;

    @GetMapping("/{storeId}/missions")
    public List<MissionResDto> getStoreMissions(@PathVariable Long storeId) {
        return missionService.getMissionsByStore(storeId);
    }
}


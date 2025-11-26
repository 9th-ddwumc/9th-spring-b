package com.example.practice_spring.domain.mission.controller;

import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.service.MemberMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    // 진행 중인 미션 조회
    @GetMapping("/{memberId}/missions/in-progress")
    public List<MissionResDto> getInProgressMissions(
            @PathVariable Long memberId
    ) {
        return memberMissionService.getInProgressMissions(memberId);
    }

    // 미션 완료시 iscomplete 값 변경
    @PatchMapping("/{memberId}/missions/{memberMissionId}/complete")
    public MissionResDto completeMission(
            @PathVariable Long memberId,
            @PathVariable Long memberMissionId
    ) {
        return memberMissionService.completeMission(memberId, memberMissionId);
    }
}


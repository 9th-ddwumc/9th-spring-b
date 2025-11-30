package com.workbook.umc9th1.mission.controller;

import com.workbook.umc9th1.mission.dto.MissionChallengeReqDto;
import com.workbook.umc9th1.mission.dto.MissionChallengeResDto;
import com.workbook.umc9th1.mission.service.MemberMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions/challenge")
public class MemberMissionController {
    private final MemberMissionService memberMissionService;

    @PostMapping
    public MissionChallengeResDto challengeMission(
            @RequestHeader("memberId") Long memberId,
            @RequestBody MissionChallengeReqDto request) {

        return memberMissionService.challengeMission(memberId, request);
    }
}

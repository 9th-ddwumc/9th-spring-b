package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionMemberDto;
import com.example.umc9th.domain.mission.repository.MissionMemberRepository;
import lombok.RequiredArgsConstructor;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MissionMemberRepository memberMissionRepository;

    @GetMapping("/{memberId}/missions")
    public ApiResponse<List<MissionMemberDto>> getMemberMissions(@PathVariable Long memberId) {
        List<MissionMemberDto> missions = memberMissionRepository.findMissionMemberDto(memberId);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, missions);
    }
}

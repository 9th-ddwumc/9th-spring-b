package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionMemberDto;
import com.example.umc9th.domain.mission.repository.MissionMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionRepository memberMissionRepository;

    @GetMapping("/{memberId}/missions")
    public List<MissionMemberDto> getMemberMissions(@PathVariable Long memberId) {
        return memberMissionRepository.findMissionMemberDto(memberId);
    }
}

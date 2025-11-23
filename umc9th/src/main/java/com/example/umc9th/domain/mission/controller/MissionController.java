package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionRepository missionRepository;

    @GetMapping("/incomplete")
    public ApiResponse<Page<MissionDto>> getIncompleteMissions(
            @RequestParam Long memberId,
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("deadline").ascending());
        Page<MissionDto> missions = missionRepository.findIncompleteMissionsByLocation(memberId, location, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, missions);
    }
}

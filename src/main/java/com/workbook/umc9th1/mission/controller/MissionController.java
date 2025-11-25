package com.workbook.umc9th1.mission.controller;

import com.workbook.umc9th1.global.apiPayload.ApiResponse;
import com.workbook.umc9th1.mission.dto.MissionCreateReqDto;
import com.workbook.umc9th1.mission.dto.MissionCreateResDto;
import com.workbook.umc9th1.mission.dto.MissionResDto;
import com.workbook.umc9th1.mission.exception.code.MissionSuccessCode;
import com.workbook.umc9th1.mission.service.MissionQueryService;
import com.workbook.umc9th1.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public MissionCreateResDto createMission(@RequestBody MissionCreateReqDto request) {
        return missionService.createMission(request);
    }

    // 특정 가게의 미션 목록 조회
    @GetMapping("/store")
    public ApiResponse<MissionResDto.MissionListResponse> getStoreMissions(
            @RequestParam String storeName,
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionQueryService.findStoreMissions(storeName, page)
        );
    }

    // 내가 진행 중인 미션 목록
    @GetMapping("/my")
    public ApiResponse<MissionResDto.MissionListResponse> getMyOngoingMissions(
            @RequestParam Long memberId,
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionQueryService.findMyOngoingMissions(memberId, page)
        );
    }

    //미션 완료
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDto.MissionListItem> completeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionQueryService.completeMission(memberId, missionId)
        );
    }
}

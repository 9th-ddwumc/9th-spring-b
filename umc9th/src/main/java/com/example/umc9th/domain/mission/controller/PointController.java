package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.PointDto;
import com.example.umc9th.domain.mission.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

    private final PointRepository pointRepository;

    @GetMapping("/{memberId}")
    public ApiResponse<List<PointDto>> getMemberPoints(@PathVariable Long memberId) {
        List<PointDto> points = pointRepository.findMemberPoints(memberId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                points
        );
    }
}


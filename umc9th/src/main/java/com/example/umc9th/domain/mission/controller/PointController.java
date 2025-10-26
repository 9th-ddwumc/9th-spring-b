package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.PointDto;
import com.example.umc9th.domain.mission.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

    private final PointRepository pointRepository;

    @GetMapping("/{memberId}")
    public List<PointDto> getMemberPoints(@PathVariable Long memberId) {
        return pointRepository.findMemberPoints(memberId);
    }
}

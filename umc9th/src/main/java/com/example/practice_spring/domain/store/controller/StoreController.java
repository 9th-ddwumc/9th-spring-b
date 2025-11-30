package com.example.practice_spring.domain.store.controller;

import com.example.practice_spring.domain.store.dto.req.StoreReqDto;
import com.example.practice_spring.domain.store.dto.res.StoreResDto;
import com.example.practice_spring.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 특정 지역에 가게 추가
    @PostMapping("/{locationId}/stores")
    public StoreResDto addStore(
            @PathVariable Long locationId,
            @RequestBody StoreReqDto reqDto
    ) {
        return storeService.addStoreToLocation(locationId, reqDto);
    }
}

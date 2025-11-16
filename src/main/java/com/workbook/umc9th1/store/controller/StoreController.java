package com.workbook.umc9th1.store.controller;

import com.workbook.umc9th1.store.dto.StoreCreateReqDto;
import com.workbook.umc9th1.store.dto.StoreCreateResDto;
import com.workbook.umc9th1.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public StoreCreateResDto createStore(@RequestBody StoreCreateReqDto request) {
        return storeService.createStore(request);
    }
}

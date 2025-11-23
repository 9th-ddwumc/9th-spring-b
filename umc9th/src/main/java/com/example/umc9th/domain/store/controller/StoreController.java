package com.example.umc9th.domain.store.controller;

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

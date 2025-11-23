package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.store.Repository.StoreRepository;
import com.example.umc9th.domain.store.dto.StoreCreateReqDto;
import com.example.umc9th.domain.store.dto.StoreCreateResDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreCreateResDto createStore(StoreCreateReqDto request) {
        Store store = Store.builder()
                .address(request.getAddress())
                .name(request.getStoreName())
                .tel(request.getTel())
                .build();

        storeRepository.save(store);

        return new StoreCreateResDto(
                store.getId(),
                store.getName(),
                store.getAddress()
        );

    }
}

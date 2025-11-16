package com.workbook.umc9th1.store.service;

import com.workbook.umc9th1.food.domain.Food;
import com.workbook.umc9th1.food.repository.FoodRepository;
import com.workbook.umc9th1.store.domain.Store;
import com.workbook.umc9th1.store.dto.StoreCreateReqDto;
import com.workbook.umc9th1.store.dto.StoreCreateResDto;
import com.workbook.umc9th1.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;

    public StoreCreateResDto createStore(StoreCreateReqDto request) {
        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Food 카테고리입니다."));

        Store store = Store.builder()
                .food(food)
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

package com.example.practice_spring.domain.store.service;

import com.example.practice_spring.domain.store.dto.req.StoreReqDto;
import com.example.practice_spring.domain.store.dto.res.StoreResDto;
import com.example.practice_spring.domain.store.entity.Location;
import com.example.practice_spring.domain.store.entity.Store;
import com.example.practice_spring.domain.store.repository.LocationRepository;
import com.example.practice_spring.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;

    public StoreResDto addStoreToLocation(Long locationId, StoreReqDto reqDto) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 존재하지 않습니다."));

        Store store = new Store();
        store.setName(reqDto.getName());
        store.setManager_number(reqDto.getManagerId() != null ? BigInteger.valueOf(reqDto.getManagerId()) : null);
        store.setDetail_address(reqDto.getDetailAddress());
        store.setLocation(location);

        Store savedStore = storeRepository.save(store);

        return new StoreResDto(
                savedStore.getStoreId(),
                savedStore.getName(),
                savedStore.getManager_number(),
                savedStore.getDetail_address(),
                savedStore.getLocation().getLocationId()
        );
    }
}

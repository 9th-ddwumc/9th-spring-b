package com.example.practice_spring.domain.store.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class StoreResDto {
    private Long storeId;
    private String name;
    private BigInteger managerId;
    private String detailAddress;
    private Long locationId;
}

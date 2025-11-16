package com.workbook.umc9th1.store.dto;

import com.workbook.umc9th1.store.enums.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreCreateResDto {

    private Long storeId;
    private String storeName;
    private Address address;
}

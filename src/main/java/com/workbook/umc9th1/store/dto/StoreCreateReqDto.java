package com.workbook.umc9th1.store.dto;

import com.workbook.umc9th1.store.enums.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreCreateReqDto {
    private Long foodId;
    private Address address;
    private String storeName;
    private String tel;
}

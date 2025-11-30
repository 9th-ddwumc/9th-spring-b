package com.example.practice_spring.domain.store.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreReqDto {
    private String name;
    private Long managerId;
    private String detailAddress;
}

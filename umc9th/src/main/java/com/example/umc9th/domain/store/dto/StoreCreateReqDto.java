package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreCreateReqDto {
    private Long foodId;
    private RabbitConnectionDetails.Address address;
    private String storeName;
    private String tel;
}

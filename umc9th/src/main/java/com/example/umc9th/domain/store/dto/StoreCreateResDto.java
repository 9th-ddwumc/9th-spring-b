package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Getter
@AllArgsConstructor
public class StoreCreateResDto {

    private Long storeId;
    private String storeName;
    private RabbitConnectionDetails.Address address;
}

package com.example.practice_spring.domain.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "store")
@Getter
@Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String name;

    private BigInteger manager_number;

    private String detail_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")  // ← 외래키 이름
    private Location location;
}

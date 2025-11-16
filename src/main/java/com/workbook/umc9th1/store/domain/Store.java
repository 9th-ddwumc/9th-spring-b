package com.workbook.umc9th1.store.domain;

import com.workbook.umc9th1.food.domain.Food;
import com.workbook.umc9th1.global.entity.BaseEntity;
import com.workbook.umc9th1.store.enums.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;


    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Address address;


    @Column(length = 50)
    private String name;


    @Column(length = 30)
    private String tel;


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorePhoto> photos = new ArrayList<>();


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreHour> hours = new ArrayList<>();
}

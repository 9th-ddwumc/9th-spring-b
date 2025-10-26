package com.workbook.umc9th1.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType name;
}
enum FoodType { KOREAN, CHINESE, JAPANESE, WESTERN, CHICKEN, PIZZA, BURGER, DESSERT, CAFE, ETC }
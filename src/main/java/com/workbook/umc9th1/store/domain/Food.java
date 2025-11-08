package com.workbook.umc9th1.store.domain;

import com.workbook.umc9th1.global.entity.BaseEntity;
import com.workbook.umc9th1.store.enums.FoodType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType foodType;
}
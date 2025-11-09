package com.example.jpa_practice.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "food_name", length = 20)
    private String foodName;

    // 연관관계 매핑
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserFood> userFoods = new ArrayList<>();

    @OneToMany(mappedBy = "food2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserFood> userFoods2 = new ArrayList<>();
}

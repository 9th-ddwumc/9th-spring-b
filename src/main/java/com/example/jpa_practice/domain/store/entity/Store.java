package com.example.jpa_practice.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name", length = 20, nullable = false)
    private String storeName;

    @Column(name = "category", length = 20, nullable = false)
    private String category;

    @Column(name = "manager_number", nullable = false)
    private Integer managerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // 연관관계 매핑 - 임시로 주석 처리
    // @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @Builder.Default
    // private List<Mission> missions = new ArrayList<>();
}

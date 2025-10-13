package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "market")
@Getter @Setter
public class Market extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Location location;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "manager_number", length = 100)
    private Long managerNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}

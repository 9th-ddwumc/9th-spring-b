package com.workbook.umc9th1.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_photo")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StorePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    @Column(name = "store_photo_url", length = 255)
    private String url;
}

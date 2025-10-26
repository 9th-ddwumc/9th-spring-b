package com.workbook.umc9th1.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_hour")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    @Column(name = "day_of_week", length = 9)
    private String dayOfWeek;


    @Column(name = "open_time")
    private java.time.LocalTime openTime;


    @Column(name = "close_time")
    private java.time.LocalTime closeTime;
}

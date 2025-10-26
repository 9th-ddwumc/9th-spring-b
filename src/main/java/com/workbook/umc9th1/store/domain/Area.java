package com.workbook.umc9th1.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "area")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20, nullable = false)
    private String name;
}

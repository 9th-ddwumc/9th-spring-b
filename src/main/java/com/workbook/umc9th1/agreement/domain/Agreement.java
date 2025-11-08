package com.workbook.umc9th1.agreement.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agreement")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20, nullable = false)
    private String name; // 약관명


    @Lob
    private String content; // 약관 내용


    @Column(name = "is_required", nullable = false)
    private boolean required; // 필수 동의 여부
}

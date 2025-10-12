package com.workbook.umc9th1.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_photo")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;


    @Column(name = "review_photo_url", length = 255)
    private String url;
}

package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviewreply")
@Getter @Setter
public class ReviewReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( nullable = false)
    private Review review;
}

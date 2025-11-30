package com.example.practice_spring.domain.review.entity;

import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private float star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private LocalDateTime updatedAt;

    // public 생성자 추가
    public Review(String content, float star, Store store, Member member) {
        this.content = content;
        this.star = star;
        this.store = store;
        this.member = member;
        this.createdAt = LocalDateTime.now();
    }

    // 리뷰 수정용 메서드
    public void updateContent(String content, float star, LocalDateTime updatedAt) {
        this.content = content;
        this.star = star;
        this.updatedAt = updatedAt;
    }
}

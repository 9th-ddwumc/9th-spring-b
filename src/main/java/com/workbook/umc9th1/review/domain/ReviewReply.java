package com.workbook.umc9th1.review.domain;

import com.workbook.umc9th1.global.entity.BaseEntity;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_reply")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewReply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replier_id", nullable = false)
    private Member replier; // 사장님(가게 운영자)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    @Lob
    private String content;
}

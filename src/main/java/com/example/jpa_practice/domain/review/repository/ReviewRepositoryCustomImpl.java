package com.example.jpa_practice.domain.review.repository;

import com.example.jpa_practice.domain.review.dto.MyReviewResponseDto;
import com.example.jpa_practice.domain.review.entity.QComment;
import com.example.jpa_practice.domain.review.entity.QReview;
import com.example.jpa_practice.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyReviewResponseDto> findMyReviews(
            Long userId,
            String storeName,
            Float minStar,
            Float maxStar) {

        QReview review = QReview.review;
        QStore store = QStore.store;
        QComment comment = QComment.comment;

        // 동적 조건을 위한 BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();

        // 필수 조건: 내가 작성한 리뷰
        builder.and(review.user.userId.eq(userId));

        // 동적 조건 1: 가게별 필터
        if (storeName != null && !storeName.trim().isEmpty()) {
            builder.and(store.storeName.eq(storeName));
        }

        // 동적 조건 2: 별점 범위 필터
        if (minStar != null) {
            builder.and(review.star.goe(minStar)); // >=
        }
        if (maxStar != null) {
            builder.and(review.star.loe(maxStar)); // <=
        }

        // QueryDSL 쿼리 실행
        // 먼저 리뷰 정보만 가져오고, 댓글은 null로 처리 (간단한 버전)
        return queryFactory
                .select(Projections.constructor(
                        MyReviewResponseDto.class,
                        review.reviewId.as("id"),
                        review.reviewContent.as("content"),
                        review.star.as("star"),
                        com.querydsl.core.types.dsl.Expressions.stringTemplate("''").as("reply") // 기본값 빈 문자열
                ))
                .from(review)
                .join(review.store, store)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}


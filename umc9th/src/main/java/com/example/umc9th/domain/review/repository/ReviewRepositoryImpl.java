package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDetailDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewReply;
import com.example.umc9th.domain.member.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewDetailDto> findReviewDetails(Long reviewId) {
        QReview review = QReview.review;
        QReviewReply reply = QReviewReply.reviewReply;
        QMember member = QMember.member;

        return queryFactory
                .select(Projections.constructor(ReviewDetailDto.class,
                        review.id,
                        member.nickname,
                        review.star,
                        review.createdAt,
                        review.content,
                        reply.content
                ))
                .from(review)
                .join(review.member, member)
                .leftJoin(review.reviewReply, reply)
                .where(review.id.eq(reviewId))
                .fetch();
    }
}

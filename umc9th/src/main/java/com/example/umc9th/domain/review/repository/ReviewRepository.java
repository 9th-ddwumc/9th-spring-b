package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.*;
import com.example.umc9th.domain.review.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT new com.example.dto.ReviewDetailResponse(
            r.reviewId,
            m.nickname,
            r.rating,
            r.createdAt,
            r.description,
            rr.content
        )
        FROM Review r
        JOIN r.member m
        LEFT JOIN r.reviewReplies rr
        WHERE r.reviewId = :reviewId
        """)
    List<ReviewDetailResponse> findReviewDetails(@Param("reviewId") Long reviewId);
}

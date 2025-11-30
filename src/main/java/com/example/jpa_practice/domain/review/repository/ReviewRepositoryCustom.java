package com.example.jpa_practice.domain.review.repository;

import com.example.jpa_practice.domain.review.dto.MyReviewResponseDto;

import java.util.List;

public interface ReviewRepositoryCustom {
    /**
     * 내가 작성한 리뷰 조회 (QueryDSL 사용)
     * 필터링 조건: 가게별, 별점별
     * 
     * @param userId 필수: 사용자 ID
     * @param storeName 선택: 가게명 필터
     * @param minStar 선택: 별점 최소값
     * @param maxStar 선택: 별점 최대값
     * @return 내가 작성한 리뷰 목록
     */
    List<MyReviewResponseDto> findMyReviews(
            Long userId,
            String storeName,
            Float minStar,
            Float maxStar
    );
}


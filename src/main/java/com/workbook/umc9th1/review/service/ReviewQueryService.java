package com.workbook.umc9th1.review.service;

import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryService {
    Page<ReviewResDto.ReviewResponseDto> getMyReviews(
            Long memberId, Long storeId, String storeName, Integer rating, Pageable pageable
    );

    @Transactional
    ReviewResDto.ReviewResponseDto createReview(
            Long memberId, ReviewRequestDto request
    );

    // 검색 API
    List<Review> searchReview(
            String filter,
            String type
    ) throws Exception;

    ReviewResDto.ReviewPreViewListDto findReview(
            String storeName,
            Integer page
    );
}

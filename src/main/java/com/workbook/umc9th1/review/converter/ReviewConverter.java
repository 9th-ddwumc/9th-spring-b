package com.workbook.umc9th1.review.converter;

import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResponseDto;

public class ReviewConverter {

    // RequestDto → Entity
    public static Review toEntity(ReviewRequestDto dto) {
        return Review.builder()
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();
    }

    // Entity → ResponseDto
    public static ReviewResponseDto toResponseDto(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

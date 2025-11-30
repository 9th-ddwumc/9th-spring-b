package com.workbook.umc9th1.review.converter;

import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {

    // RequestDto → Entity
    public static Review toEntity(ReviewRequestDto dto) {
        return Review.builder()
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();
    }

    // Entity → ResponseDto
    public static ReviewResDto.ReviewResponseDto toResponseDto(Review review) {
        return ReviewResDto.ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // result -> DTO
    public static ReviewResDto.ReviewPreViewListDto toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDto.ReviewPreViewListDto.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDto.ReviewPreViewDto toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getReviewer().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}

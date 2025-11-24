package com.example.jpa_practice.domain.review.converter;

import com.example.jpa_practice.domain.review.dto.ReviewResDTO;
import com.example.jpa_practice.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public final class ReviewConverter {

    private ReviewConverter() {
    }

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreViewDTO> reviewList = reviewPage
                .stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .toList();

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getNickname())
                .score(review.getStar())
                .body(review.getReviewContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}

package com.example.practice_spring.domain.review.converter;

import com.example.practice_spring.domain.review.dto.res.MyReviewResDto;
import com.example.practice_spring.domain.review.entity.Review;

public class ReviewConverter {

    public static MyReviewResDto toDto(Review review) {
        return new MyReviewResDto(
                review.getReviewId(),
                review.getContent(),
                review.getStar(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getMember().getMemberId(),
                review.getStore().getStoreId()
        );
    }
}

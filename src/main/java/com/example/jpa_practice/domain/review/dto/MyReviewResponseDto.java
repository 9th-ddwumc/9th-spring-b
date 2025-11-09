package com.example.jpa_practice.domain.review.dto;

public record MyReviewResponseDto(
        Long id,           // review_id
        String content,    // review_content
        Float star,       // star (별점)
        String reply       // comment_content (답글) - null 가능
) {}


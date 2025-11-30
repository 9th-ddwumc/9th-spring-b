package com.example.jpa_practice.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewWithStoreDto(
        Long reviewId,
        String reviewContent,
        Float star,
        LocalDateTime createdAt,
        String storeName,
        String category,
        String nickname
) {}

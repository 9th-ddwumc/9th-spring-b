package com.example.jpa_practice.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private String reviewContent;
    private Float star;
    private Long storeId;
    private Long userId;
    
    public ReviewRequestDto(String reviewContent, Float star, Long storeId, Long userId) {
        this.reviewContent = reviewContent;
        this.star = star;
        this.storeId = storeId;
        this.userId = userId;
    }
}

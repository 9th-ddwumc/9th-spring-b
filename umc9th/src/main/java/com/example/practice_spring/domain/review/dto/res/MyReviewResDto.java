package com.example.practice_spring.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewResDto {
    private Long reviewId;
    private String content;
    private float star;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long storeId;
    private Long memberId;
}

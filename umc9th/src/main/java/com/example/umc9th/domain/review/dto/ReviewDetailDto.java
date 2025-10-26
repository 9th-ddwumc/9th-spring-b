package com.example.umc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDetailDto {
    private Long reviewId;
    private String nickname;
    private Integer rating;
    private java.time.LocalDateTime createdAt;
    private String description;
    private String replyContent;
}

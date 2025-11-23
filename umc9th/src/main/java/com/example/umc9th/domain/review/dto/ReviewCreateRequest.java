package com.example.umc9th.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewCreateRequest {
    private Integer rating;
    private String content;
}

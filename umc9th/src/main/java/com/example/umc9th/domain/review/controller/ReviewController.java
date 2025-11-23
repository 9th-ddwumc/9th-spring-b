package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDetailDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/{reviewId}")
    public ApiResponse<List<ReviewDetailDto>> getReviewDetails(@PathVariable Long reviewId) {
        List<ReviewDetailDto> reviewDetails = reviewRepository.findReviewDetails(reviewId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewDetails);
    }
}

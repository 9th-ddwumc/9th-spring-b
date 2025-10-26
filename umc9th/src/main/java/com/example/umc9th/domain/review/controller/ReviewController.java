package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDetailDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/{reviewId}")
    public List<ReviewDetailDto> getReviewDetails(@PathVariable Long reviewId) {
        return reviewRepository.findReviewDetails(reviewId);
    }
}

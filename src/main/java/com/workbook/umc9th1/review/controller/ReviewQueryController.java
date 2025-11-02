package com.workbook.umc9th1.review.controller;

import com.workbook.umc9th1.review.dto.MyReviewDto;
import com.workbook.umc9th1.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewQueryController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my-review")
    public Page<MyReviewDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewQueryService.getMyReviews(memberId, storeId, storeName, rating, pageable);
    }
}

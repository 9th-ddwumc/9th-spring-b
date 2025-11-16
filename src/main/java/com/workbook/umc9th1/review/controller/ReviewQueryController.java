package com.workbook.umc9th1.review.controller;

import com.workbook.umc9th1.global.apiPayload.ApiResponse;
import com.workbook.umc9th1.global.apiPayload.code.GeneralSuccessCode;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResponseDto;
import com.workbook.umc9th1.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewQueryController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my-review")
    public ApiResponse<Page<ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ReviewResponseDto> result =
                reviewQueryService.getMyReviews(memberId, storeId, storeName, rating, pageable);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }

    @PostMapping
    public ReviewResponseDto createReview(
            @RequestHeader("memberId") Long memberId,
            @RequestBody ReviewRequestDto request
    ) {
        return reviewQueryService.createReview(memberId, request);
    }
}

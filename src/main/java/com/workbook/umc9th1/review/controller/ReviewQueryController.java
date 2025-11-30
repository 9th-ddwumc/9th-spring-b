package com.workbook.umc9th1.review.controller;

import com.workbook.umc9th1.global.apiPayload.ApiResponse;
import com.workbook.umc9th1.global.apiPayload.code.GeneralSuccessCode;
import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResDto;
import com.workbook.umc9th1.review.exception.code.ReviewSuccessCode;
import com.workbook.umc9th1.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewQueryController implements ReviewControllerDocs{
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my-review")
    public ApiResponse<Page<ReviewResDto.ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ReviewResDto.ReviewResponseDto> result =
                reviewQueryService.getMyReviews(memberId, storeId, storeName, rating, pageable);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }

    @PostMapping
    public ReviewResDto.ReviewResponseDto createReview(
            @RequestHeader("memberId") Long memberId,
            @RequestBody ReviewRequestDto request
    ) {
        return reviewQueryService.createReview(memberId, request);
    }

    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam String filter,
            @RequestParam String type
    ) throws Exception{
        List<Review> result = reviewQueryService.searchReview(filter, type);
        return result;
    }

    // 가게의 리뷰 목록 조회
    @GetMapping
    @Override
    public ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}

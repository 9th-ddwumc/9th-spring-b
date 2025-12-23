package com.example.practice_spring.domain.review.controller;

import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.review.dto.req.MyReviewReqDto;
import com.example.practice_spring.domain.review.dto.res.MyReviewResDto;
import com.example.practice_spring.domain.review.service.ReviewService;
import com.example.practice_spring.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/member/{memberId}/store/{storeId}")
    public MyReviewResDto createReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody MyReviewReqDto reqDto
    ) {
        // Member와 Store는 실제로 DB 조회 후 전달해야 함
        Member member = new Member(); // 예시, 실제로는 memberRepository.findById(memberId)
        member.setMemberId(memberId);

        Store store = new Store(); // 예시, 실제로는 storeRepository.findById(storeId)
        store.setStoreId(storeId);

        return reviewService.createReview(member, store, reqDto);
    }

    // 리뷰 수정
    @PatchMapping("/{reviewId}")
    public MyReviewResDto updateReview(
            @PathVariable Long reviewId,
            @RequestBody MyReviewReqDto reqDto
    ) {
        return reviewService.updateReview(reviewId, reqDto);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    // 특정 가게 리뷰 조회
    @GetMapping("/store/{storeId}")
    public List<MyReviewResDto> getReviewsByStore(@PathVariable Long storeId) {
        return reviewService.getReviewsByStore(storeId);
    }

    // 특정 회원 리뷰 조회
    @GetMapping("/member/{memberId}")
    public List<MyReviewResDto> getReviewsByMember(@PathVariable Long memberId) {
        return reviewService.getReviewsByMember(memberId);
    }
}

package com.example.practice_spring.domain.review.service;

import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.review.converter.ReviewConverter;
import com.example.practice_spring.domain.review.dto.req.MyReviewReqDto;
import com.example.practice_spring.domain.review.dto.res.MyReviewResDto;
import com.example.practice_spring.domain.review.entity.Review;
import com.example.practice_spring.domain.review.repository.ReviewRepository;
import com.example.practice_spring.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // 리뷰 작성
    public MyReviewResDto createReview(Member member, Store store, MyReviewReqDto myReviewReqDto) {
        Review review = new Review(
                myReviewReqDto.getContent(),
                myReviewReqDto.getStar(),
                store,
                member
        );
        return ReviewConverter.toDto(reviewRepository.save(review));
    }

    // 리뷰 수정
    public MyReviewResDto updateReview(Long reviewId, MyReviewReqDto reqDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
        review.updateContent(reqDto.getContent(), reqDto.getStar(), LocalDateTime.now());
        return ReviewConverter.toDto(reviewRepository.save(review));
    }

    // 리뷰 삭제
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
        reviewRepository.delete(review);
    }

    // 특정 가게 리뷰 조회
    public List<MyReviewResDto> getReviewsByStore(Long storeId) {
        return reviewRepository.findByStore_StoreId(storeId)
                .stream()
                .map(ReviewConverter::toDto)
                .collect(Collectors.toList());
    }

    // 특정 회원 리뷰 조회
    public List<MyReviewResDto> getReviewsByMember(Long memberId) {
        return reviewRepository.findByMember_MemberId(memberId)
                .stream()
                .map(ReviewConverter::toDto)
                .collect(Collectors.toList());
    }
}

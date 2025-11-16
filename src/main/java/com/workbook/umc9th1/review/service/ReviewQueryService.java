package com.workbook.umc9th1.review.service;

import com.querydsl.core.BooleanBuilder;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.repository.MemberRepository;
import com.workbook.umc9th1.review.converter.ReviewConverter;
import com.workbook.umc9th1.review.domain.QReview;
import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResponseDto;
import com.workbook.umc9th1.review.exception.code.ReviewErrorCode;
import com.workbook.umc9th1.review.repository.ReviewRepository;
import com.workbook.umc9th1.store.domain.Store;
import com.workbook.umc9th1.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Page<ReviewResponseDto> getMyReviews(Long memberId, Long storeId, String storeName, Integer rating, Pageable pageable) {
        if (!memberRepository.existsById(memberId)) {
            throw new GeneralException(ReviewErrorCode.MEMBER_NOT_FOUND);
        }
        if (rating != null && (rating < 0 || rating > 5)) {
            throw new GeneralException(ReviewErrorCode.INVALID_RATING_RANGE);
        }
        if (pageable == null || pageable.getPageNumber() < 0 || pageable.getPageSize() <= 0) {
            throw new GeneralException(ReviewErrorCode.INVALID_PAGE_REQUEST);
        }

        try {
            QReview r = QReview.review;

            BooleanBuilder where = new BooleanBuilder()
                    .and(r.reviewer.id.eq(memberId));

            // 가게별 필터링
            if (storeId != null) {
                where.and(r.store.id.eq(storeId));
            }
            if (storeName != null && !storeName.isBlank()) {
                where.and(r.store.name.eq(storeName));
            }

            // 별점별 필터링
            if (rating != null) {
                if (rating == 5) {
                    where.and(r.rating.goe(new BigDecimal("5.0")));
                } else {
                    BigDecimal from = BigDecimal.valueOf(rating);
                    BigDecimal to = BigDecimal.valueOf(rating + 1);
                    where.and(r.rating.goe(from).and(r.rating.lt(to)));
                }
            }

            Page<Review> reviewPage = reviewRepository.searchMyReviews(where, pageable);

            return reviewPage.map(ReviewConverter::toResponseDto);
        } catch (Exception e) {
            throw new GeneralException(ReviewErrorCode.REVIEW_QUERY_FAILED);
        }
    }

    @Transactional
    public ReviewResponseDto createReview(Long memberId, ReviewRequestDto request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ReviewErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GeneralException(ReviewErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .reviewer(member)
                .store(store)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        reviewRepository.save(review);

        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

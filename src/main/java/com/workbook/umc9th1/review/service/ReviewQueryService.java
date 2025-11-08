package com.workbook.umc9th1.review.service;

import com.querydsl.core.BooleanBuilder;
import com.workbook.umc9th1.review.domain.QReview;
import com.workbook.umc9th1.review.dto.MyReviewDto;
import com.workbook.umc9th1.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<MyReviewDto> getMyReviews(Long memberId, Long storeId, String storeName, Integer rating, Pageable pageable) {
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

        return reviewRepository.searchMyReviews(where, pageable);
    }
}

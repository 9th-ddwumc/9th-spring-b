package com.workbook.umc9th1.review.repository;

import com.querydsl.core.types.Predicate;
import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {

    Page<MyReviewDto> searchMyReviews(
            Predicate predicate, Pageable pageable
    );
}

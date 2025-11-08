package com.workbook.umc9th1.review.repository;

import com.querydsl.core.types.Predicate;
import com.workbook.umc9th1.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {

    Page<Review> searchMyReviews(
            Predicate predicate, Pageable pageable
    );
}

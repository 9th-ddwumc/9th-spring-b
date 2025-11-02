package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDetailDto;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewDetailDto> findReviewDetails(Long reviewId);
}

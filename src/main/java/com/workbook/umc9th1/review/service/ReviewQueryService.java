package com.workbook.umc9th1.review.service;

import com.querydsl.core.BooleanBuilder;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.repository.MemberRepository;
import com.workbook.umc9th1.review.converter.ReviewConverter;
import com.workbook.umc9th1.review.domain.QReview;
import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.review.dto.req.ReviewRequestDto;
import com.workbook.umc9th1.review.dto.res.ReviewResDto;
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
import java.util.List;

public interface ReviewQueryService {
    Page<ReviewResDto.ReviewResponseDto> getMyReviews(
            Long memberId, Long storeId, String storeName, Integer rating, Pageable pageable
    );

    @Transactional
    ReviewResDto.ReviewResponseDto createReview(
            Long memberId, ReviewRequestDto request
    );

    // 검색 API
    List<Review> searchReview(
            String filter,
            String type
    ) throws Exception;

    ReviewResDto.ReviewPreViewListDto findReview(
            String storeName,
            Integer page
    );
}

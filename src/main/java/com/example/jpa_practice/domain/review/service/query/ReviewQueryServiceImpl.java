package com.example.jpa_practice.domain.review.service.query;

import com.example.jpa_practice.domain.review.converter.ReviewConverter;
import com.example.jpa_practice.domain.review.dto.ReviewResDTO;
import com.example.jpa_practice.domain.review.entity.Review;
import com.example.jpa_practice.domain.review.repository.ReviewRepository;
import com.example.jpa_practice.domain.store.entity.Store;
import com.example.jpa_practice.domain.store.repository.StoreRepository;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getReviews(int page, int size) {
        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                Math.max(size, 1)
        );

        Page<Review> reviewPage = reviewRepository.findAllByOrderByCreatedAtDesc(pageable);

        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page) {
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "Store not found with name: " + storeName
                ));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long userId, int pageIndex) {
        if (userId == null || userId <= 0) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "userId는 1 이상의 값이어야 합니다."
            );
        }

        PageRequest pageRequest = PageRequest.of(pageIndex, 10);
        Page<Review> result = reviewRepository.findByUserUserIdOrderByCreatedAtDesc(userId, pageRequest);
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}


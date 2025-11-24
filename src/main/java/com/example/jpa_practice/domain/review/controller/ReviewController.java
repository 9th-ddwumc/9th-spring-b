package com.example.jpa_practice.domain.review.controller;

import com.example.jpa_practice.domain.review.dto.MyReviewResponseDto;
import com.example.jpa_practice.domain.review.dto.ReviewRequestDto;
import com.example.jpa_practice.domain.review.dto.ReviewResDTO;
import com.example.jpa_practice.domain.review.dto.ReviewWithStoreDto;
import com.example.jpa_practice.domain.review.entity.Review;
import com.example.jpa_practice.domain.review.repository.ReviewRepository;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.domain.store.entity.Store;
import com.example.jpa_practice.domain.store.repository.StoreRepository;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.apiPayload.code.GeneralSuccessCode;
import com.example.jpa_practice.global.exception.CustomException;
import com.example.jpa_practice.domain.review.exception.code.ReviewSuccessCode;
import com.example.jpa_practice.domain.review.service.query.ReviewQueryService;
import com.example.jpa_practice.global.annotation.PositivePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewQueryService reviewQueryService;

    /**
     * 리뷰 작성 API
     * 원본 SQL: INSERT INTO review (review_content, star, created_at, store_id, user_id) 
     *           VALUES ('음 너무 맛있어요...', 5.0, NOW(), [가게 ID], [사용자 ID]);
     */
    @PostMapping
    public ApiResponse<Review> createReview(@RequestBody ReviewRequestDto requestDto) {
        // 사용자와 가게 존재 여부 확인
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "User not found with id: " + requestDto.getUserId()
                ));
        
        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "Store not found with id: " + requestDto.getStoreId()
                ));

        // 리뷰 생성
        Review review = Review.builder()
                .reviewContent(requestDto.getReviewContent())
                .star(requestDto.getStar())
                .createdAt(LocalDateTime.now())
                .user(user)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, savedReview);
    }

    /**
     * 메서드 이름으로 쿼리 생성 방식들 테스트
     */
    
    @GetMapping("/user/{userId}")
    public ApiResponse<List<Review>> getUserReviews(@PathVariable Long userId) {
        List<Review> reviews = reviewRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<List<Review>> getStoreReviews(@PathVariable Long storeId) {
        List<Review> reviews = reviewRepository.findByStoreStoreIdOrderByCreatedAtDesc(storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/star/{minStar}")
    public ApiResponse<List<Review>> getReviewsByMinStar(@PathVariable Float minStar) {
        List<Review> reviews = reviewRepository.findByStarGreaterThanEqualOrderByCreatedAtDesc(minStar);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/star/between")
    public ApiResponse<List<Review>> getReviewsByStarRange(
            @RequestParam Float minStar,
            @RequestParam Float maxStar) {
        List<Review> reviews = reviewRepository.findByStarBetweenOrderByCreatedAtDesc(minStar, maxStar);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/paged")
    public ApiResponse<Page<Review>> getAllReviewsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc(pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/store/{storeId}/paged")
    public ApiResponse<Page<Review>> getStoreReviewsPaged(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewRepository.findByStoreStoreIdOrderByCreatedAtDesc(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    /**
     * @Query 어노테이션을 사용한 JPQL 쿼리들 테스트
     */
    
    @GetMapping("/user/{userId}/store/{storeId}")
    public ApiResponse<List<Review>> getUserStoreReviews(
            @PathVariable Long userId,
            @PathVariable Long storeId) {
        List<Review> reviews = reviewRepository.findByUserAndStore(userId, storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/with-store-info")
    public ApiResponse<List<ReviewWithStoreDto>> getReviewsWithStoreInfo() {
        List<ReviewWithStoreDto> reviews = reviewRepository.findReviewsWithStoreInfo();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    @GetMapping("/store/{storeId}/average-star")
    public ApiResponse<Double> getStoreAverageStar(@PathVariable Long storeId) {
        Double averageStar = reviewRepository.findAverageStarByStoreId(storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, averageStar != null ? averageStar : 0.0);
    }

    @GetMapping("/store/{storeId}/count")
    public ApiResponse<Long> getStoreReviewCount(@PathVariable Long storeId) {
        Long count = reviewRepository.countReviewsByStoreId(storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, count);
    }

    /**
     * 특정 리뷰 조회
     */
    @GetMapping("/{reviewId}")
    public ApiResponse<Review> getReview(@PathVariable Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "Review not found with id: " + reviewId
                ));
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, review);
    }

    /**
     * 리뷰 수정
     */
    @PutMapping("/{reviewId}")
    public ApiResponse<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto requestDto) {
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(
                        GeneralErrorCode.NOT_FOUND,
                        "Review not found with id: " + reviewId
                ));

        review = Review.builder()
                .reviewId(reviewId)
                .reviewContent(requestDto.getReviewContent())
                .star(requestDto.getStar())
                .createdAt(review.getCreatedAt()) // 기존 생성일 유지
                .user(review.getUser())
                .store(review.getStore())
                .build();

        Review updatedReview = reviewRepository.save(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, updatedReview);
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteReview(@PathVariable Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new CustomException(
                    GeneralErrorCode.NOT_FOUND,
                    "Review not found with id: " + reviewId
            );
        }
        reviewRepository.deleteById(reviewId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK);
    }

    /**
     * 내가 작성한 리뷰 보기 API (QueryDSL 사용)
     * 필터링 조건: 가게별, 별점별
     * 하나의 API로 설계
     * 
     * @param userId 필수: 사용자 ID
     * @param storeName 선택: 가게명 필터 (예: "반이학생마라탕마라반")
     * @param starRange 선택: 별점 구간 필터 ("5", "4", "3", "2", "1")
     *                  5: 5.0점, 4: 4.0~4.9점, 3: 3.0~3.9점, 2: 2.0~2.9점, 1: 1.0~1.9점
     * @return 내가 작성한 리뷰 목록
     */
    @GetMapping("/my-reviews")
    public ApiResponse<List<MyReviewResponseDto>> getMyReviews(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String starRange) {
        
        // userId 유효성 검증
        if (userId == null || userId <= 0) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "userId must be greater than zero."
            );
        }
        
        // 별점 구간을 Float 범위로 변환
        Float minStar = null;
        Float maxStar = null;
        
        if (starRange != null && !starRange.trim().isEmpty()) {
            try {
                int range = Integer.parseInt(starRange.trim());
                if (range >= 1 && range <= 5) {
                    minStar = (float) range;
                    if (range == 5) {
                        maxStar = 5.0f; // 5점은 정확히 5.0
                    } else {
                        maxStar = (float) (range + 0.9); // 예: "3" → 3.0~3.9
                    }
                }
            } catch (NumberFormatException e) {
                // 잘못된 starRange 값은 무시
            }
        }
        
        try {
            List<MyReviewResponseDto> reviews = reviewRepository.findMyReviews(
                    userId, storeName, minStar, maxStar);
            return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
        } catch (Exception e) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "리뷰 조회 중 오류가 발생했습니다."
            );
        }
    }

    @GetMapping
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "0") int page
    ) {
        ReviewResDTO.ReviewPreViewListDTO response = reviewQueryService.findReview(storeName, page);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, response);
    }

    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviewList(
            @RequestParam Long userId,
            @PositivePage int page
    ) {
        ReviewResDTO.ReviewPreViewListDTO response = reviewQueryService.getMyReviews(userId, page);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, response);
    }
}

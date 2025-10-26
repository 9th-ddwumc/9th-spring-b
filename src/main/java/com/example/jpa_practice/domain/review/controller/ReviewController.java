package com.example.jpa_practice.domain.review.controller;

import com.example.jpa_practice.domain.review.dto.ReviewRequestDto;
import com.example.jpa_practice.domain.review.dto.ReviewWithStoreDto;
import com.example.jpa_practice.domain.review.entity.Review;
import com.example.jpa_practice.domain.review.repository.ReviewRepository;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.domain.store.entity.Store;
import com.example.jpa_practice.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    /**
     * 리뷰 작성 API
     * 원본 SQL: INSERT INTO review (review_content, star, created_at, store_id, user_id) 
     *           VALUES ('음 너무 맛있어요...', 5.0, NOW(), [가게 ID], [사용자 ID]);
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequestDto requestDto) {
        // 사용자와 가게 존재 여부 확인
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDto.getUserId()));
        
        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + requestDto.getStoreId()));

        // 리뷰 생성
        Review review = Review.builder()
                .reviewContent(requestDto.getReviewContent())
                .star(requestDto.getStar())
                .createdAt(LocalDateTime.now())
                .user(user)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.ok(savedReview);
    }

    /**
     * 메서드 이름으로 쿼리 생성 방식들 테스트
     */
    
    @GetMapping("/user/{userId}")
    public List<Review> getUserReviews(@PathVariable Long userId) {
        return reviewRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
    }

    @GetMapping("/store/{storeId}")
    public List<Review> getStoreReviews(@PathVariable Long storeId) {
        return reviewRepository.findByStoreStoreIdOrderByCreatedAtDesc(storeId);
    }

    @GetMapping("/star/{minStar}")
    public List<Review> getReviewsByMinStar(@PathVariable Float minStar) {
        return reviewRepository.findByStarGreaterThanEqualOrderByCreatedAtDesc(minStar);
    }

    @GetMapping("/star/between")
    public List<Review> getReviewsByStarRange(
            @RequestParam Float minStar,
            @RequestParam Float maxStar) {
        return reviewRepository.findByStarBetweenOrderByCreatedAtDesc(minStar, maxStar);
    }

    @GetMapping("/paged")
    public Page<Review> getAllReviewsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @GetMapping("/store/{storeId}/paged")
    public Page<Review> getStoreReviewsPaged(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findByStoreStoreIdOrderByCreatedAtDesc(storeId, pageable);
    }

    /**
     * @Query 어노테이션을 사용한 JPQL 쿼리들 테스트
     */
    
    @GetMapping("/user/{userId}/store/{storeId}")
    public List<Review> getUserStoreReviews(
            @PathVariable Long userId,
            @PathVariable Long storeId) {
        return reviewRepository.findByUserAndStore(userId, storeId);
    }

    @GetMapping("/with-store-info")
    public List<ReviewWithStoreDto> getReviewsWithStoreInfo() {
        return reviewRepository.findReviewsWithStoreInfo();
    }

    @GetMapping("/store/{storeId}/average-star")
    public ResponseEntity<Double> getStoreAverageStar(@PathVariable Long storeId) {
        Double averageStar = reviewRepository.findAverageStarByStoreId(storeId);
        return ResponseEntity.ok(averageStar != null ? averageStar : 0.0);
    }

    @GetMapping("/store/{storeId}/count")
    public ResponseEntity<Long> getStoreReviewCount(@PathVariable Long storeId) {
        Long count = reviewRepository.countReviewsByStoreId(storeId);
        return ResponseEntity.ok(count);
    }

    /**
     * 특정 리뷰 조회
     */
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
        return ResponseEntity.ok(review);
    }

    /**
     * 리뷰 수정
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto requestDto) {
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        review = Review.builder()
                .reviewId(reviewId)
                .reviewContent(requestDto.getReviewContent())
                .star(requestDto.getStar())
                .createdAt(review.getCreatedAt()) // 기존 생성일 유지
                .user(review.getUser())
                .store(review.getStore())
                .build();

        Review updatedReview = reviewRepository.save(review);
        return ResponseEntity.ok(updatedReview);
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.ok().build();
    }
}

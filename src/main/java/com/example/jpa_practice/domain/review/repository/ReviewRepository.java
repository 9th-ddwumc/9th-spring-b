package com.example.jpa_practice.domain.review.repository;

import com.example.jpa_practice.domain.review.dto.ReviewWithStoreDto;
import com.example.jpa_practice.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    
    /**
     * 메서드 이름으로 쿼리 생성 방식들
     */
    
    // 1. 특정 사용자의 모든 리뷰 조회
    List<Review> findByUserUserIdOrderByCreatedAtDesc(Long userId);
    
    // 2. 특정 가게의 모든 리뷰 조회
    List<Review> findByStoreStoreIdOrderByCreatedAtDesc(Long storeId);
    
    // 3. 특정 점수 이상의 리뷰 조회
    List<Review> findByStarGreaterThanEqualOrderByCreatedAtDesc(Float minStar);
    
    // 4. 특정 점수 범위의 리뷰 조회
    List<Review> findByStarBetweenOrderByCreatedAtDesc(Float minStar, Float maxStar);
    
    // 5. 페이징을 포함한 리뷰 조회
    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    // 6. 특정 가게의 페이징 리뷰 조회
    Page<Review> findByStoreStoreIdOrderByCreatedAtDesc(Long storeId, Pageable pageable);
    
    /**
     * @Query 어노테이션을 사용한 JPQL 쿼리들
     */
    
    // 7. 특정 사용자의 특정 가게 리뷰 조회
    @Query("SELECT r FROM Review r WHERE r.user.userId = :userId AND r.store.storeId = :storeId ORDER BY r.createdAt DESC")
    List<Review> findByUserAndStore(@Param("userId") Long userId, @Param("storeId") Long storeId);
    
    // 8. 리뷰와 가게 정보를 함께 조회하는 DTO 프로젝션
    @Query("SELECT new com.example.jpa_practice.domain.review.dto.ReviewWithStoreDto(" +
            "r.reviewId, " +
            "r.reviewContent, " +
            "r.star, " +
            "r.createdAt, " +
            "s.storeName, " +
            "s.category, " +
            "u.nickname) " +
            "FROM Review r " +
            "JOIN r.store s " +
            "JOIN r.user u " +
            "ORDER BY r.createdAt DESC")
    List<ReviewWithStoreDto> findReviewsWithStoreInfo();
    
    // 9. 특정 가게의 평균 별점 조회
    @Query("SELECT AVG(r.star) FROM Review r WHERE r.store.storeId = :storeId")
    Double findAverageStarByStoreId(@Param("storeId") Long storeId);
    
    // 10. 특정 가게의 리뷰 개수 조회
    @Query("SELECT COUNT(r) FROM Review r WHERE r.store.storeId = :storeId")
    Long countReviewsByStoreId(@Param("storeId") Long storeId);
}

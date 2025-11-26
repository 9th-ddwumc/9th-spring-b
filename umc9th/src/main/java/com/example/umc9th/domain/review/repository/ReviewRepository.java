package com.example.practice_spring.domain.review.repository;

import com.example.practice_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게 리뷰 조회
    List<Review> findByStore_StoreId(Long storeId);

    // 특정 회원 리뷰 조회
    List<Review> findByMember_MemberId(Long memberId);
}

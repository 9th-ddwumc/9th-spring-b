package com.example.jpa_practice.domain.review.repository;

import com.example.jpa_practice.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

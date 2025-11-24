package com.example.jpa_practice.domain.review.service.query;

import com.example.jpa_practice.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {

    ReviewResDTO.ReviewPreViewListDTO getReviews(int page, int size);

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);

    ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long userId, int pageIndex);
}
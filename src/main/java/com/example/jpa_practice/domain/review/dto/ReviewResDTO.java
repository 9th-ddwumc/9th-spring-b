package com.example.jpa_practice.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    private ReviewResDTO() {
    }

    /**
     * 리뷰 프리뷰 리스트 응답 DTO.
     */
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    /**
     * 단일 리뷰 프리뷰 DTO.
     */
    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ) {
    }
}


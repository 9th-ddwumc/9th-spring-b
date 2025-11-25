package com.workbook.umc9th1.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ReviewResDto {
    @Builder
    public record ReviewResponseDto(
            Long reviewId,
            Long storeId,
            String storeName,
            BigDecimal rating,
            String content,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewPreViewListDto(
            List<ReviewPreViewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDto(
            String ownerNickname,
            BigDecimal score,
            String body,
            LocalDate createdAt
    ){}


}

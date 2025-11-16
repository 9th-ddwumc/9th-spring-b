package com.workbook.umc9th1.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {
    private Long reviewId;
    private Long storeId;
    private String storeName;
    private BigDecimal rating;
    private String content;
    private LocalDateTime createdAt;
}

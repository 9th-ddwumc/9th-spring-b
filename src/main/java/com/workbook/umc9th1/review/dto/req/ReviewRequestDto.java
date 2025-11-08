package com.workbook.umc9th1.review.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {
    private Long memberId;
    private Long storeId;
    private String storeName;
    private BigDecimal rating;
    private String content;
}

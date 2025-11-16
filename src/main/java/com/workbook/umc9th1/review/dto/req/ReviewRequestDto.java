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

    private Long storeId;
    private BigDecimal rating;
    private String content;
}

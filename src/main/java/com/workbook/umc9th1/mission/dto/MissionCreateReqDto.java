package com.workbook.umc9th1.mission.dto;

import com.workbook.umc9th1.global.annotation.ExistRewardPoint;
import com.workbook.umc9th1.global.annotation.ExistStore;
import com.workbook.umc9th1.global.annotation.FutureEndDay;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MissionCreateReqDto {
    @ExistStore
    private Long storeId;
    @NotBlank
    private String content;
    @ExistRewardPoint
    private Integer rewardPoint;
    @FutureEndDay
    private LocalDateTime endDay;
}

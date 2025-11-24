package com.example.jpa_practice.domain.review.exception.code;

import com.example.jpa_practice.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "REVIEW200_1", "리뷰 목록을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

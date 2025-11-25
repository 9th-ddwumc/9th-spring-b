package com.workbook.umc9th1.review.exception.code;

import com.workbook.umc9th1.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 조회하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

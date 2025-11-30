package com.workbook.umc9th1.review.exception.code;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "REVIEW400_1", "존재하지 않는 회원입니다."),
    INVALID_RATING_RANGE(HttpStatus.BAD_REQUEST, "REVIEW400_2", "rating은 0~5 범위의 정수여야 합니다."),
    INVALID_PAGE_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_3", "페이지 요청이 유효하지 않습니다."),
    REVIEW_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "REIVEW500_1", "리뷰 목록 조회에 실패했습니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "REVIEW400_4", "존재하지 않는 가게입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

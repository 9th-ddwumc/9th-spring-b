package com.workbook.umc9th1.mission.exception.code;

import com.workbook.umc9th1.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 회원가입했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK("200", "요청이 성공적으로 처리되었습니다."),
    CREATED("201", "리소스가 성공적으로 생성되었습니다."),
    UPDATED("2001", "리소스가 성공적으로 수정되었습니다."),
    DELETED("2002", "리소스가 성공적으로 삭제되었습니다.");

    private final String code;
    private final String message;
}

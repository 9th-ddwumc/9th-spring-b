package com.example.practice_spring.global.apiPayload.code;

public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS("M200", "회원가입이 성공적으로 완료되었습니다."),
    LOGIN_SUCCESS("M201", "로그인에 성공했습니다."),
    FOUND("M202", "요청이 성공적으로 처리되었습니다.");

    private final String code;
    private final String message;

    MemberSuccessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

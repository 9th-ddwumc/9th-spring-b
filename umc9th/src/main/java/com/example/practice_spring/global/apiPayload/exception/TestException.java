package com.example.practice_spring.global.apiPayload.exception;

import com.example.practice_spring.global.apiPayload.code.BaseErrorCode;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

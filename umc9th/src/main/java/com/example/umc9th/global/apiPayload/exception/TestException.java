package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

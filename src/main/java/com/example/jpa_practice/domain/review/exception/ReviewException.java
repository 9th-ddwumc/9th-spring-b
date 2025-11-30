package com.example.jpa_practice.domain.review.exception;

import com.example.jpa_practice.global.apiPayload.code.BaseErrorCode;
import com.example.jpa_practice.global.exception.CustomException;

public class ReviewException extends CustomException {

    public ReviewException(BaseErrorCode code) {
        super(code);
    }

    public ReviewException(BaseErrorCode code, String message) {
        super(code, message);
    }
}


package com.example.jpa_practice.domain.member.exception;

import com.example.jpa_practice.domain.member.exception.code.MemberErrorCode;
import com.example.jpa_practice.global.apiPayload.code.BaseErrorCode;
import com.example.jpa_practice.global.exception.CustomException;

public class MemberException extends CustomException {

    public MemberException(BaseErrorCode code) {
        super(code);
    }

    public MemberException(BaseErrorCode code, String message) {
        super(code, message);
    }
}


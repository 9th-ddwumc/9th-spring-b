package com.workbook.umc9th1.member.exception;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}

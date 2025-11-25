package com.workbook.umc9th1.test.exception;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

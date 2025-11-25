package com.workbook.umc9th1.food.exception;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}

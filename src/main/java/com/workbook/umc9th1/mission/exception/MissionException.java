package com.workbook.umc9th1.mission.exception;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}

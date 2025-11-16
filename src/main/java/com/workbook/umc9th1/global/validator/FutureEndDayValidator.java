package com.workbook.umc9th1.global.validator;

import com.workbook.umc9th1.global.annotation.FutureEndDay;
import com.workbook.umc9th1.mission.exception.code.MissionErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class FutureEndDayValidator implements ConstraintValidator<FutureEndDay, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {

        if (value == null) return true;

        boolean valid = value.isAfter(LocalDateTime.now());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MissionErrorCode.INVALID_END_DAY.getMessage()
            ).addConstraintViolation();
        }

        return valid;
    }
}

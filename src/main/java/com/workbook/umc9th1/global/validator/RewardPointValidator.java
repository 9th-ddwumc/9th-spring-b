package com.workbook.umc9th1.global.validator;

import com.workbook.umc9th1.global.annotation.ExistRewardPoint;
import com.workbook.umc9th1.mission.exception.code.MissionErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RewardPointValidator implements ConstraintValidator<ExistRewardPoint, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;

        boolean valid = value >= 0;

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MissionErrorCode.INVALID_REWARD_POINT.getMessage()
            ).addConstraintViolation();
        }

        return valid;
    }
}

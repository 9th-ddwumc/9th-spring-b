package com.workbook.umc9th1.global.validator;

import com.workbook.umc9th1.global.annotation.ExistStore;
import com.workbook.umc9th1.mission.exception.code.MissionErrorCode;
import com.workbook.umc9th1.store.repository.StoreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if (value == null) return true;

        boolean exists = storeRepository.existsById(value);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MissionErrorCode.STORE_NOT_FOUND.getMessage()
            ).addConstraintViolation();
        }

        return exists;
    }
}

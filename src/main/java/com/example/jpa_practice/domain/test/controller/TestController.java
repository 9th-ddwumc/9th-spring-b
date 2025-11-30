package com.example.jpa_practice.domain.test.controller;

import com.example.jpa_practice.domain.test.converter.TestConverter;
import com.example.jpa_practice.domain.test.dto.TestResDTO;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.apiPayload.code.GeneralSuccessCode;
import com.example.jpa_practice.global.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                TestConverter.toTestingDTO("This is Test!")
        );
    }

    @GetMapping("/test/exception")
    public ApiResponse<Void> testException(@RequestParam(defaultValue = "0") int flag) {
        if (flag == 1) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "테스트 예외가 발생했습니다."
            );
        } else if (flag == 2) {
            throw new CustomException(
                    GeneralErrorCode.UNAUTHORIZED,
                    "인증이 필요합니다."
            );
        }
        return ApiResponse.onSuccess(GeneralSuccessCode.OK);
    }
}


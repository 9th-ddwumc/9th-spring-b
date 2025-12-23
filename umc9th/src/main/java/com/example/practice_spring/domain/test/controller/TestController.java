package com.example.practice_spring.domain.test.controller;

import com.example.practice_spring.domain.test.converter.TestConverter;
import com.example.practice_spring.domain.test.dto.res.TestResDTO;
import com.example.practice_spring.global.apiPayload.ApiResponse;
import com.example.practice_spring.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("This is Test!")
        );
    }
}

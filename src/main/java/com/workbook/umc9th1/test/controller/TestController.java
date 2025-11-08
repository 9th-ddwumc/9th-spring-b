package com.workbook.umc9th1.test.controller;

import com.workbook.umc9th1.global.apiPayload.ApiResponse;
import com.workbook.umc9th1.global.apiPayload.code.GeneralErrorCode;
import com.workbook.umc9th1.global.apiPayload.code.GeneralSuccessCode;
import com.workbook.umc9th1.test.converter.TestConverter;
import com.workbook.umc9th1.test.dto.res.TestResDTO;
import com.workbook.umc9th1.test.exception.TestException;
import com.workbook.umc9th1.test.service.query.TestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
//        throw new TestException(GeneralErrorCode.INTERNAL_SERVER_ERROR);
        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("This is Test!")
        );
    }

    // 예외 상황
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ) {
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("This is Test!"));
    }
}

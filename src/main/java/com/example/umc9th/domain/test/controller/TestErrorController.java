package com.example.umc9th.domain.test.controller;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestErrorController {

    @GetMapping("/error")
    public ResponseEntity<ApiResponse<String>> triggerError() {
        String s = null;
        s.length(); // 여기서 NullPointerException 발생

        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, "이 코드는 실행되지 않습니다."));
    }
}

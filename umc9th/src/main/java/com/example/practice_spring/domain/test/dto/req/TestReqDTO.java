package com.example.practice_spring.domain.test.dto.req;

import lombok.Builder;
import lombok.Getter;

public class TestReqDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }
}

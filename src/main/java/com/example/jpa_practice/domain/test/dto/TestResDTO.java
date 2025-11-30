package com.example.jpa_practice.domain.test.dto;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    private TestResDTO() {
    }

    @Getter
    @Builder
    public static class Testing {
        private final String testString;
    }
}


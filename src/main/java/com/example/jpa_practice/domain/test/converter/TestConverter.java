package com.example.jpa_practice.domain.test.converter;

import com.example.jpa_practice.domain.test.dto.TestResDTO;

public final class TestConverter {

    private TestConverter() {
    }

    public static TestResDTO.Testing toTestingDTO(String testing) {
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }
}


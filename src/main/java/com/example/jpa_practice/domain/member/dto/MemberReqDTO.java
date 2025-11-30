package com.example.jpa_practice.domain.member.dto;

import com.example.jpa_practice.domain.member.enums.Address;
import com.example.jpa_practice.domain.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @Email(message = "올바른 이메일 형식이 아닙니다.")
            @NotBlank(message = "이메일은 필수입니다.")
            String email, // 추가된 속성

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password, // 추가된 속성

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth,

            @NotNull(message = "주소는 필수입니다.")
            Address address,

            @NotBlank(message = "상세 주소는 필수입니다.")
            String specAddress,

            @NotNull(message = "선호 카테고리는 필수입니다.")
            List<Long> preferCategory // @ExistFoods 어노테이션은 나중에 추가 가능
    ) {}

    // 로그인
    @Schema(description = "로그인 요청 DTO")
    public record LoginDTO(
            @NotBlank(message = "이메일은 필수입니다.")
            @Schema(description = "이메일", example = "test@example.com")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            @Schema(description = "비밀번호", example = "Test1234!", format = "password")
            String password
    ) {}
}


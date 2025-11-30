package com.example.jpa_practice.domain.member.dto;

import com.example.jpa_practice.domain.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Schema(description = "닉네임", example = "hong")
    private String nickname;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Schema(description = "이메일", example = "test@example.com")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @Schema(description = "비밀번호 (최소 8자, 영문/숫자/특수문자 포함)", 
            example = "Test1234!", 
            format = "password")
    private String password;

    @NotNull(message = "성별은 필수입니다.")
    @Schema(description = "성별", example = "MALE")
    private Gender gender;

    @NotNull(message = "생년월일은 필수입니다.")
    @Schema(description = "생년월일", example = "1990-01-01")
    private LocalDate birth;

    @NotBlank(message = "주소는 필수입니다.")
    @Schema(description = "주소", example = "서울시 강남구")
    private String address;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$",
            message = "올바른 전화번호 형식이 아닙니다.")
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
}


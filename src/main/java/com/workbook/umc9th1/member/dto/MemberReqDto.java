package com.workbook.umc9th1.member.dto;

import com.workbook.umc9th1.global.annotation.ExistFoods;
import com.workbook.umc9th1.member.enums.Gender;
import com.workbook.umc9th1.store.enums.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {
    public record JoinDto(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String nickname,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}

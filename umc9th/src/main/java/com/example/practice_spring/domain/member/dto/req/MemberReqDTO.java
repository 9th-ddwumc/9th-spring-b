package com.example.practice_spring.domain.member.dto.req;

import com.example.practice_spring.domain.member.entity.type.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            GenderType gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            List<Long> preferCategory
    ){}

    public record LoginDTO(
    @NotBlank
    String email,
    @NotBlank
    String password
    ){}
}

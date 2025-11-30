package com.example.jpa_practice.domain.member.exception.code;

import com.example.jpa_practice.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "사용자를 찾을 수 없습니다."),
    INVALID(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}


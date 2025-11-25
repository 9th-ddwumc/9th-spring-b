package com.workbook.umc9th1.mission.exception.code;

import com.workbook.umc9th1.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION400_1", "존재하지 않는 가게입니다."),
    INVALID_REWARD_POINT(HttpStatus.BAD_REQUEST, "MISSION400_2", "rewardPoint는 0 이상이어야 합니다."),
    INVALID_END_DAY(HttpStatus.BAD_REQUEST, "MISSION400_3", "endDay는 현재 시각 이후여야 합니다."),
    CHALLENGE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MISSION400_4", "이미 도전 중인 미션입니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION400_1", "존재하지 않는 미션입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION400_5", "존재하지 않는 회원입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

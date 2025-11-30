package com.example.jpa_practice.domain.member.converter;

import com.example.jpa_practice.domain.member.dto.MemberReqDTO;
import com.example.jpa_practice.domain.member.dto.MemberResDTO;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.enums.Role;

public final class MemberConverter {

    private MemberConverter() {
    }

    // DTO, Salted Password, Role -> Entity
    public static User toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return User.builder()
                .name(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)         // 추가된 코드
                .birth(dto.birth())
                .address(dto.address().name()) // Address enum을 String으로 변환
                .detailAddress(dto.specAddress()) // 상세 주소
                .gender(dto.gender())
                .build();
    }

    // Entity, AccessToken -> LoginDTO
    public static MemberResDTO.LoginDTO toLoginDTO(User member, String accessToken) {
        return new MemberResDTO.LoginDTO(
                member.getUserId(),
                accessToken
        );
    }
}


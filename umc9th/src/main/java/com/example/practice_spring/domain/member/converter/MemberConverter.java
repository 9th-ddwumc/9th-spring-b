package com.example.practice_spring.domain.member.converter;

import com.example.practice_spring.domain.member.dto.req.MemberReqDTO;
import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.member.entity.type.GenderType;

import java.time.ZoneId;
import java.util.Date;

public class MemberConverter {

    public static Member toEntity(MemberReqDTO.JoinDTO dto) {
        Member member = new Member();
        member.setName(dto.name());
        member.setGender(dto.gender());
        member.setBirth(Date.from(dto.birth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        member.setAddress(dto.address());
        member.setDetailAddress(dto.specAddress());
        member.setPoint(0);
        return member;
    }
}

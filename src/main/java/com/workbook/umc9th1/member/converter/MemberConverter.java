package com.workbook.umc9th1.member.converter;

import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;

public class MemberConverter {

    // Entity -> Dto
    public static MemberResDto.JoinDto toJoinDto(
            Member member
    ) {
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // Dto -> Entity
    public static Member toMember(
            MemberReqDto.JoinDto joinDto
    ) {
       return Member.builder()
               .name(joinDto.name())
               .birth(joinDto.birth())
               .address(joinDto.address())
               .detailAddress(joinDto.specAddress())
               .gender(joinDto.gender())
               .build();
    }
}

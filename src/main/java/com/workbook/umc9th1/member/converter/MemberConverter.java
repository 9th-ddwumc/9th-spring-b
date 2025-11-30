package com.workbook.umc9th1.member.converter;

import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;
import com.workbook.umc9th1.member.enums.Provider;
import com.workbook.umc9th1.member.enums.Role;

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
            MemberReqDto.JoinDto joinDto,
            String password,
            Role role
    ) {
       return Member.builder()
               .name(joinDto.name())
               .email(joinDto.email())
               .password(password)
               .role(role)
               .nickname(joinDto.nickname())
               .birth(joinDto.birth())
               .address(joinDto.address())
               .detailAddress(joinDto.specAddress())
               .gender(joinDto.gender())
               .provider(Provider.LOCAL)
               .providerId("LOCAL_" + joinDto.nickname())
               .build();
    }
}

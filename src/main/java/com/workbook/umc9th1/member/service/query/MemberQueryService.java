package com.workbook.umc9th1.member.service.query;

import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDto.LoginDto login(MemberReqDto.@Valid LoginDto dto);
}

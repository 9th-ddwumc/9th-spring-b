package com.workbook.umc9th1.member.service.query;

import com.workbook.umc9th1.global.auth.CustomUserDetails;
import com.workbook.umc9th1.global.util.JwtUtil;
import com.workbook.umc9th1.member.converter.MemberConverter;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;
import com.workbook.umc9th1.member.exception.MemberException;
import com.workbook.umc9th1.member.exception.code.MemberErrorCode;
import com.workbook.umc9th1.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDto.LoginDto login(
            MemberReqDto.@Valid LoginDto dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDto(member, accessToken);
    }
}

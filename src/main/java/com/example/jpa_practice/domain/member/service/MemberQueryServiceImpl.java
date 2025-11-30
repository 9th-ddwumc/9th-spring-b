package com.example.jpa_practice.domain.member.service;

import com.example.jpa_practice.domain.member.converter.MemberConverter;
import com.example.jpa_practice.domain.member.dto.MemberReqDTO;
import com.example.jpa_practice.domain.member.dto.MemberResDTO;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.exception.MemberException;
import com.example.jpa_practice.domain.member.exception.code.MemberErrorCode;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.global.auth.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final UserRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {
        // Member 조회
        User member = memberRepository.findByEmailAndDeletedAtIsNull(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}


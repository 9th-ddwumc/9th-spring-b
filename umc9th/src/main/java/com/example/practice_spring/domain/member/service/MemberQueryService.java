package com.example.practice_spring.domain.member.service;

import com.example.practice_spring.domain.member.dto.req.MemberReqDTO;
import com.example.practice_spring.domain.member.dto.res.MemberResDTO;
import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.member.repository.MemberRepository;
import com.example.practice_spring.domain.member.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return new MemberResDTO.LoginDTO(
                member.getId(),
                member.getEmail(),
                member.getName(),
                MemberSuccessCode.LOGIN_SUCCESS.getMessage()
        );
    }
}

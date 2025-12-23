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
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // Member 엔티티 생성
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .name(dto.getName())
                .role("USER") // 기본 ROLE
                .build();

        memberRepository.save(member);

        return new MemberResDTO.JoinDTO(
                member.getId(),
                member.getEmail(),
                member.getName(),
                MemberSuccessCode.SIGN_UP_SUCCESS.getMessage()
        );
    }
}

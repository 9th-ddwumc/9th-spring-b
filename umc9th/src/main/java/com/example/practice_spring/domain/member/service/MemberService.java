package com.example.practice_spring.domain.member.service;

import com.example.practice_spring.domain.member.converter.MemberConverter;
import com.example.practice_spring.domain.member.dto.req.MemberReqDTO;
import com.example.practice_spring.domain.member.entity.Member;
import com.example.practice_spring.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public Long join(MemberReqDTO.JoinDTO dto, String email, String password) {

        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = MemberConverter.toEntity(dto);

        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));

        Member saved = memberRepository.save(member);

        return saved.getMemberId();
    }

    // 로그인
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return member;
    }
}

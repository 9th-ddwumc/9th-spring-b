package com.workbook.umc9th1.global.auth;

import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.exception.MemberException;
import com.workbook.umc9th1.member.exception.code.MemberErrorCode;
import com.workbook.umc9th1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}

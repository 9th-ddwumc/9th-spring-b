package com.example.jpa_practice.domain.member.service;

import com.example.jpa_practice.domain.member.converter.MemberConverter;
import com.example.jpa_practice.domain.member.dto.MemberReqDTO;
import com.example.jpa_practice.domain.member.dto.MemberResDTO;
import com.example.jpa_practice.domain.member.entity.User;
import com.example.jpa_practice.domain.member.enums.Role;
import com.example.jpa_practice.domain.member.repository.UserRepository;
import com.example.jpa_practice.global.exception.CustomException;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 이메일 중복 확인
        if (userRepository.existsByEmailAndDeletedAtIsNull(dto.email())) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "이미 존재하는 이메일입니다."
            );
        }

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        User member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // 추가 필드 설정
        // MemberConverter에서 이미 기본 필드들을 설정했으므로, 나머지 필드만 추가
        // 하지만 User는 @Builder를 사용하므로, 필드를 직접 설정할 수 없음
        // 따라서 모든 필드를 포함하여 다시 빌드해야 함
        User finalMember = User.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .birth(member.getBirth())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .gender(member.getGender())
                .point(0)
                .socialType(com.example.jpa_practice.domain.member.enums.SocialType.KAKAO)
                .updatedAt(LocalDateTime.now())
                .nickname(dto.name()) // 임시로 name을 nickname으로 사용
                .phoneNumber("") // 임시값, 필요시 DTO에 추가
                .build();
        
        member = finalMember;

        // DB에 저장
        User savedMember = userRepository.save(member);

        return new MemberResDTO.JoinDTO(
                savedMember.getUserId(),
                savedMember.getEmail(),
                savedMember.getName(),
                "회원가입이 완료되었습니다."
        );
    }
}


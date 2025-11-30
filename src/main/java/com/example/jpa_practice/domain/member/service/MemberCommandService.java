package com.example.jpa_practice.domain.member.service;

import com.example.jpa_practice.domain.member.dto.MemberReqDTO;
import com.example.jpa_practice.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}


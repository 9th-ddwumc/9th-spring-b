package com.workbook.umc9th1.mission.service;

import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.domain.mapping.MemberMission;
import com.workbook.umc9th1.member.repository.MemberRepository;
import com.workbook.umc9th1.mission.domain.Mission;
import com.workbook.umc9th1.mission.dto.MissionChallengeReqDto;
import com.workbook.umc9th1.mission.dto.MissionChallengeResDto;
import com.workbook.umc9th1.mission.enums.MissionStatus;
import com.workbook.umc9th1.mission.exception.code.MissionErrorCode;
import com.workbook.umc9th1.mission.repository.MemberMissionRepository;
import com.workbook.umc9th1.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionChallengeResDto challengeMission(
            Long memberId, MissionChallengeReqDto request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        if (memberMissionRepository.existsByMember_IdAndMission_Id(memberId, request.getMissionId())) {
            throw new GeneralException(MissionErrorCode.CHALLENGE_ALREADY_EXISTS);
        }

        // 4) 새로운 도전 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .requestedAt(LocalDateTime.now())
                .build();

        memberMissionRepository.save(memberMission);

        return MissionChallengeResDto.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .status(memberMission.getStatus())
                .build();
    }
}

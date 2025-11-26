package com.example.practice_spring.domain.mission.service;

import com.example.practice_spring.domain.mission.converter.MissionConverter;
import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.entity.MemberMission;
import com.example.practice_spring.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MissionResDto> getInProgressMissions(Long memberId) {

        List<MemberMission> list =
                memberMissionRepository.findByMember_MemberIdAndIsCompleteFalse(memberId);

        return list.stream()
                .map(mm -> MissionConverter.toDto(mm.getMission()))
                .toList();
    }

    public MissionResDto completeMission(Long memberId, Long memberMissionId) {

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다."));

        if (!memberMission.getMember().getMemberId().equals(memberId)) {
            throw new IllegalStateException("본인의 미션만 완료할 수 있습니다.");
        }

        memberMission.setIsComplete(true);

        memberMissionRepository.save(memberMission);

        return MissionConverter.toDto(memberMission.getMission());
    }
}


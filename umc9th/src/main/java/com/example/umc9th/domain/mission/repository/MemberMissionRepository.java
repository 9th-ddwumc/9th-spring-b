package com.example.practice_spring.domain.mission.repository;

import com.example.practice_spring.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByMember_MemberIdAndIsCompleteFalse(Long memberId);
}

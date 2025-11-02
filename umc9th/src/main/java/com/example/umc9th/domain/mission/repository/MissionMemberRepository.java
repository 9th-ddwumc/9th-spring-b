package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.*;
import com.example.umc9th.domain.mission.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MissionMember, Long> {

    @Query("""
        SELECT new com.example.dto.MemberMissionInfo(
            m.memberId,
            mk.name,
            p.amount,
            ms.missionId,
            ms.point,
            ms.deadline,
            mk.marketId,
            mk.category,
            mm.complete
        )
        FROM MissionMember mm
        JOIN mm.member m
        JOIN mm.mission ms
        JOIN ms.market mk
        LEFT JOIN Point p ON p.member.memberId = m.memberId
        WHERE m.memberId = :memberId
        """)
    List<MemberMissionInfo> findMemberMissionInfo(@Param("memberId") Long memberId);
}

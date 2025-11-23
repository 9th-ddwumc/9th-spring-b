package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.*;
import com.example.umc9th.domain.mission.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT new com.example.dto.MissionResponse(
            l.locationName,
            mm.missionMemberId,
            mm.complete,
            m.point,
            m.deadline,
            mk.marketId,
            mk.name
        )
        FROM Mission m
        JOIN m.market mk
        JOIN mk.location l
        LEFT JOIN MissionMember mm
            ON m.missionId = mm.mission.missionId
            AND mm.member.memberId = :memberId
        WHERE (mm.complete IS NULL OR mm.complete = false)
          AND l.locationName = :locationName
        ORDER BY m.deadline ASC
        """)
    Page<MissionResponse> findIncompleteMissionsByLocation(
            @Param("memberId") Long memberId,
            @Param("locationName") String locationName,
            Pageable pageable
    );
}

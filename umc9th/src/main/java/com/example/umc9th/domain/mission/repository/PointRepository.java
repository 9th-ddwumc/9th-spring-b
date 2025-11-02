package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.*;
import com.example.umc9th.domain.mission.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("""
        SELECT new com.example.dto.MemberPointResponse(
            m.memberId,
            m.nickname,
            m.email,
            m.phone,
            p.amount
        )
        FROM Point p
        LEFT JOIN p.member m
        WHERE m.memberId = :memberId
        """)
    List<MemberPointResponse> findMemberPoints(@Param("memberId") Long memberId);
}

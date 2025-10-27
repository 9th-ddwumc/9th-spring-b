package com.workbook.umc9th1.mission.repository;

import com.workbook.umc9th1.member.domain.mapping.MemberMission;
import com.workbook.umc9th1.mission.dto.MissionHomeItemDto;
import com.workbook.umc9th1.mission.dto.MissionListItemDto;
import com.workbook.umc9th1.mission.enums.MissionStatus;
import com.workbook.umc9th1.store.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 전체 미션 목록
    @Query(value = """
            select new com.workbook.umc9th1.mission.dto.MissionListItemDto(
                mm.id,
                m.id,
                s.name,
                m.content,
                m.rewardPoint,
                m.endDay,
                cast(mm.status as string),
                mm.requestedAt,
                mm.completedAt
                )
                from MemberMission mm
                join mm.mission m
                join m.store s
                where mm.member.id = :memberId and (:statuses is null or mm.status in :statuses)
                order by mm.id desc
                """,
            countQuery = """
                select count(mm.id)
                from MemberMission mm
                where mm.member.id = :memberId and (:statuses is null or mm.status in :statuses)
            """
    )
    Page<MissionListItemDto> findMyMissions(
            @Param("memberId") Long missionId,
            @Param("statuses") Collection<MissionStatus> statuses,
            Pageable pageable
    );

    // 특정 상태별 미션
    @Query(
            value = """
            select new com.workbook.umc9th1.mission.dto.MissionListItemDto(
                mm.id,
                m.id,
                s.name,
                m.content,
                m.rewardPoint,
                m.endDay,
                cast(mm.status as string),
                mm.requestedAt,
                mm.completedAt
            )
            from MemberMission mm
            join mm.mission m
            join m.store s
            where mm.member.id = :memberId and mm.status = :status
            order by mm.id desc
            """,
            countQuery = """
            select count(mm.id)
            from MemberMission mm
            where mm.member.id = :memberId and mm.status = :status
            """
    )
    Page<MissionListItemDto> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    // 현재 지역 기준 도전 가능 미션 목록
    @Query("""
            select new com.workbook.umc9th1.mission.dto.MissionHomeItemDto(
            m.id,
            s.name,
            s.address,
            f.foodType,
            m.content,
            m.rewardPoint,
            m.endDay,
            cast(mm.status as string),
            cast((select count(mm2.id)
                  from MemberMission mm2
                    join mm2.mission m2
                    join m2.store s2
                  where mm2.member.id = :memberId
                    and mm2.status = :statusCompleted
                    and s2.address = s.address
            ) as long),
            concat(concat(cast(m.endDay as string), '_'), cast(m.id as string))
        )
        
        from MemberMission mm
          join mm.mission m
          join m.store s
          join s.food f
        where mm.member.id = :memberId
          and s.address = :address
          and m.endDay >= :currentDate
          and mm.status = :statusInProgress
          and (
                m.endDay > :cursorEndDay
                or (m.endDay = :cursorEndDay and m.id > :cursorId)
              )
        order by m.endDay asc, m.id asc
        """)
    List<MissionHomeItemDto> findInProgressByAddressWithCursor(
            @Param("memberId") Long memberId,
            @Param("address") Address address,
            @Param("currentDate") LocalDateTime currentDate,
            @Param("statusInProgress") MissionStatus statusInProgress,
            @Param("statusCompleted") MissionStatus statusCompleted,
            @Param("cursorEndDay") LocalDateTime cursorEndDay,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

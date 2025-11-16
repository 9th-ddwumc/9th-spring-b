package com.workbook.umc9th1.member.repository;

import com.workbook.umc9th1.member.domain.mapping.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberPointRepository extends JpaRepository<MemberPoint, Long> {
    @Query("""
        select coalesce(sum(mi.rewardPoint), 0)
        from MemberPoint mp
        left join mp.mission mi
        where mp.member.id = :memberId
        """)
    Long sumRewardPointByMemberId(Long memberId);
}

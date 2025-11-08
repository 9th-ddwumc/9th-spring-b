package com.workbook.umc9th1.member.domain.mapping;

import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.mission.domain.Mission;
import com.workbook.umc9th1.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_mission",
        uniqueConstraints = @UniqueConstraint(name = "uk_member_mission", columnNames = {"member_id", "mission_id"}))
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MissionStatus status;


    @Column(name = "requested_at")
    private LocalDateTime requestedAt;


    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
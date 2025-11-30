package com.example.practice_spring.domain.mission.entity;

import com.example.practice_spring.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="member_mission")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;

    private Boolean isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 상태 변경 메서드
    public void complete() {
        this.isComplete = true;
    }
}

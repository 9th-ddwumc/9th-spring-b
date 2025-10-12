package com.example.jpa_practice.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "mission_deadline", nullable = false)
    private LocalDate missionDeadline;

    @Column(name = "conditional", length = 50, nullable = false)
    private String conditional;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "store_id", nullable = false)
    // private Store store;

    // 연관관계 매핑
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserMission> userMissions = new ArrayList<>();
}

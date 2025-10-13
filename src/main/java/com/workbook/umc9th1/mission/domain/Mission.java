package com.workbook.umc9th1.mission.domain;

import com.workbook.umc9th1.member.domain.mapping.MemberMission;
import com.workbook.umc9th1.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    @Lob
    private String content;


    @Column(name = "reward_point")
    private Integer rewardPoint;


    @Column(name = "end_day")
    private LocalDateTime endDay;


    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> participants = new ArrayList<>();
}

package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.entity.Market;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "mission_market")
@Getter
@Setter
public class MissionMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private com.example.umc9th.domain.misson.entity.Mission mission;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @Column(name = "is_complete")
    private Boolean isComplete;
}

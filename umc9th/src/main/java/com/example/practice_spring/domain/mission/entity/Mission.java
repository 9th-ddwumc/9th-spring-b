package com.example.practice_spring.domain.mission.entity;

import com.example.practice_spring.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private LocalDate deadline;

    private String conditional;

    private Integer point;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Mission(String conditional, int point, LocalDate deadline, Store store) {
        this.conditional = conditional;
        this.point = point;
        this.deadline = deadline;
        this.store = store;
        this.createdAt = LocalDateTime.now();
    }
}

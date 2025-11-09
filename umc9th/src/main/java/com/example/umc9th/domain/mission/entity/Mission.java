package com.example.umc9th.domain.misson.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
@Getter @Setter
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dealLine")
    private LocalDateTime deadLine;

    @Column(name = "conditional", length = 50)
    private String conditional;

    @Column(name = "point")
    private Integer point;
}

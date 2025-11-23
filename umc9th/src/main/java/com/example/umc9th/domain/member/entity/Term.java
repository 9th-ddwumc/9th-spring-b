package com.example.umc9th.domain.member.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", length = 30, nullable = false)
    private String name;

    @Column (name ="content", nullable=false)
    private String content;
}

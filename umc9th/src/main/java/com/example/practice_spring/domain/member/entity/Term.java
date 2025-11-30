package com.example.practice_spring.domain.member.entity;

import com.example.practice_spring.domain.member.entity.type.TermType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "term")
@Getter
@Setter
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    private TermType name;
}

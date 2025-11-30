package com.example.practice_spring.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member_term")
@Getter
@Setter
public class MemberTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberTermId;
}

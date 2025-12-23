package com.example.practice_spring.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member_food")
@Getter
@Setter
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberFoodId;
}

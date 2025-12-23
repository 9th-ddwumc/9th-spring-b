package com.example.practice_spring.domain.member.entity;

import com.example.practice_spring.domain.member.entity.type.FoodType;
import com.example.practice_spring.domain.member.entity.type.GenderType;
import com.example.practice_spring.domain.member.entity.type.SocialType;
import com.example.practice_spring.global.auth.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String name;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private Date birth;

    private String address;

    private String detailAddress;

    private String socialUid;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private Integer point;

    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    private String nickname;

    private LocalDateTime updatedAt;

    @CreatedDate
    private LocalDateTime createdAt;
}

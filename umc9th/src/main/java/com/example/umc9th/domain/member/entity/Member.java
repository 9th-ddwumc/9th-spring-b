package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6, name="name", nullable = false)
    private String name;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth", length = 10)
    private String birth;

    @Column(name = "address", length = 30)
    private String address;

    @Column(name = "detail_address", length = 30)
    private String Detailaddress;

    @Column(name = "socail_uid", length = 20)
    private String socialUid;

    @Column(name = "socail_type", length = 3)
    private SocialType socialType;

    @Column(name = "point", length = 20)
    private Long point;

    @Column(name = "phone_number", length = 20)
    private String phone_number;

    @Column(name = "email", length = 20)
    private String email;

    // deleted_at 삭제하고 status로 회원 관리
    @Column(name = "status", length = 15)
    private Status status;
}

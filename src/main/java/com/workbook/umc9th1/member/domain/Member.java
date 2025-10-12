package com.workbook.umc9th1.member.domain;

import com.workbook.umc9th1.member.domain.mapping.MemberAgreement;
import com.workbook.umc9th1.member.domain.mapping.MemberFood;
import com.workbook.umc9th1.member.domain.mapping.MemberPoint;
import com.workbook.umc9th1.review.domain.Review;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private Provider provider;


    @Column(name = "provider_id", length = 255)
    private String providerId;


    @Column(length = 50)
    private String name;


    @Column(length = 50)
    private String nickname;


    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(length = 100)
    private String email;


    @Column(name = "phone_number", length = 20)
    private String phoneNumber;


    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;


    @Column(length = 255)
    private String address;


    private LocalDate birth;


    @Column(length = 15)
    private String status; // 활성/비활성 등 자유 텍스트


    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;


    // 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberAgreement> agreements = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<MemberFood> favoriteFoods = new ArrayList<>();


    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<MemberPoint> points = new ArrayList<>();
}
enum Provider { LOCAL, KAKAO, NAVER, GOOGLE }
enum Gender { MALE, FEMALE, NONE }
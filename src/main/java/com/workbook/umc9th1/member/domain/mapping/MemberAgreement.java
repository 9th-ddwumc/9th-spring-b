package com.workbook.umc9th1.member.domain.mapping;

import com.workbook.umc9th1.agreement.domain.Agreement;
import com.workbook.umc9th1.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_agreement",
        uniqueConstraints = @UniqueConstraint(name = "uk_member_agreement", columnNames = {"member_id", "agreement_id"}))
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreement;


    @Column(name = "agree_yn", nullable = false)
    private boolean agreeYn;


    @Column(name = "agree_date")
    private LocalDateTime agreeDate;
}

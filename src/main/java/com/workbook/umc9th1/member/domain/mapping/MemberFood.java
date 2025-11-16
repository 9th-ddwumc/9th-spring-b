package com.workbook.umc9th1.member.domain.mapping;

import com.workbook.umc9th1.food.domain.Food;
import com.workbook.umc9th1.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_food",
        uniqueConstraints = @UniqueConstraint(name = "uk_member_food", columnNames = {"member_id", "food_id"}))
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;
}

package com.workbook.umc9th1.member.repository;

import com.workbook.umc9th1.member.domain.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}

package com.workbook.umc9th1.member.repository;

import com.workbook.umc9th1.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<MemberBasics> findProjectedById(Long id);

    interface MemberBasics {
        Long getId();
        String getNickname();
        String getEmail();
        String getPhoneNumber();
    }
}

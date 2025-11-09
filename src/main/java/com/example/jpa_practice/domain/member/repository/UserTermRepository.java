package com.example.jpa_practice.domain.member.repository;

import com.example.jpa_practice.domain.member.entity.UserTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermRepository extends JpaRepository<UserTerm, Long> {
}

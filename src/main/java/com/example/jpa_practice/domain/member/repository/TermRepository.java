package com.example.jpa_practice.domain.member.repository;

import com.example.jpa_practice.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}

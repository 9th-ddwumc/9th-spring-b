package com.example.jpa_practice.domain.member.repository;

import com.example.jpa_practice.domain.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

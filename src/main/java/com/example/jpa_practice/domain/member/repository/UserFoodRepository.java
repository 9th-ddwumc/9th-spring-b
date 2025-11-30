package com.example.jpa_practice.domain.member.repository;

import com.example.jpa_practice.domain.member.entity.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}

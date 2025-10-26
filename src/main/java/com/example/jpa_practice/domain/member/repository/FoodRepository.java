package com.example.jpa_practice.domain.member.repository;

import com.example.jpa_practice.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}

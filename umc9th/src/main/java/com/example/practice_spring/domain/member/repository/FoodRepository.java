package com.example.practice_spring.domain.member.repository;

import com.example.practice_spring.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}

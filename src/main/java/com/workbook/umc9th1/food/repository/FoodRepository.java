package com.workbook.umc9th1.food.repository;

import com.workbook.umc9th1.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}

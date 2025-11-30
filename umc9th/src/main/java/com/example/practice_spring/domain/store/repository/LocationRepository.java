package com.example.practice_spring.domain.store.repository;

import com.example.practice_spring.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

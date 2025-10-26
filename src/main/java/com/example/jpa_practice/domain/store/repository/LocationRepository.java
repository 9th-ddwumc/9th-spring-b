package com.example.jpa_practice.domain.store.repository;

import com.example.jpa_practice.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

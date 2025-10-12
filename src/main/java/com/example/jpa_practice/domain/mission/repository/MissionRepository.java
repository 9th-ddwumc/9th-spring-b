package com.example.jpa_practice.domain.mission.repository;

import com.example.jpa_practice.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

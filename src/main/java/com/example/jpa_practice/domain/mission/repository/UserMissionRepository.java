package com.example.jpa_practice.domain.mission.repository;

import com.example.jpa_practice.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}

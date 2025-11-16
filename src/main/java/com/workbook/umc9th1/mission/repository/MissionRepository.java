package com.workbook.umc9th1.mission.repository;

import com.workbook.umc9th1.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

package com.example.practice_spring.domain.mission.repository;

import com.example.practice_spring.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 가게(Store)의 미션 목록 조회
    List<Mission> findByStore_StoreId(Long storeId);
}

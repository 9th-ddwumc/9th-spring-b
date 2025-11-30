package com.example.jpa_practice.domain.mission.repository;
import com.example.jpa_practice.domain.mission.dto.UserMissionDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import com.example.jpa_practice.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    /**
     * 사용자의 미션 목록을 조회하는 JPQL 쿼리
     * 원본 SQL: SELECT M.mission_id, M.conditional, M.score, S.store_name, UM.mission_success
     *           FROM user_mission AS UM
     *           INNER JOIN mission AS M ON UM.mission_id = M.mission_id
     *           INNER JOIN store AS S ON M.store_id = S.store_id
     *           WHERE UM.user_id = [사용자 ID]
     *           ORDER BY UM.created_at DESC
     *           LIMIT 10 OFFSET 0;
     */
    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.UserMissionDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "s.storeName, " +
            "um.missionSuccess) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.userId = :userId " +
            "ORDER BY um.createdAt DESC")
    Page<UserMissionDto> findUserMissionsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.UserMissionDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "s.storeName, " +
            "um.missionSuccess) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.userId = :userId AND um.missionSuccess = false " +
            "ORDER BY um.createdAt DESC")
    Page<UserMissionDto> findInProgressMissionsByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * 메서드 이름으로 쿼리 생성 방식들
     */
    
    // 1. 특정 사용자의 모든 미션 조회 (페이징 없이)
    List<UserMission> findByUserUserIdOrderByCreatedAtDesc(Long userId);
    
    // 2. 특정 사용자의 성공한 미션만 조회
    List<UserMission> findByUserUserIdAndMissionSuccessTrueOrderByCreatedAtDesc(Long userId);
    
    // 3. 특정 사용자의 실패한 미션만 조회
    List<UserMission> findByUserUserIdAndMissionSuccessFalseOrderByCreatedAtDesc(Long userId);
    
    // 4. 특정 사용자의 미션을 생성일 기준으로 내림차순 정렬하여 페이징 조회
    Page<UserMission> findByUserUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    
    // 5. 특정 사용자의 성공한 미션을 페이징으로 조회
    Page<UserMission> findByUserUserIdAndMissionSuccessTrueOrderByCreatedAtDesc(Long userId, Pageable pageable);

}

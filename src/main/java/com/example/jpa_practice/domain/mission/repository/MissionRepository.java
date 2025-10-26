package com.example.jpa_practice.domain.mission.repository;

import com.example.jpa_practice.domain.mission.dto.HomeMissionDto;
import com.example.jpa_practice.domain.mission.dto.MissionWithStoreDto;
import com.example.jpa_practice.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    /**
     * 메서드 이름으로 쿼리 생성 방식들
     */
    
    // 1. 특정 점수 이상의 미션 조회
    List<Mission> findByScoreGreaterThanEqualOrderByCreatedAtDesc(Integer score);
    
    // 2. 특정 점수 범위의 미션 조회
    List<Mission> findByScoreBetweenOrderByCreatedAtDesc(Integer minScore, Integer maxScore);
    
    // 3. 특정 마감일 이전의 미션 조회
    List<Mission> findByMissionDeadlineBeforeOrderByCreatedAtDesc(LocalDate deadline);
    
    // 4. 특정 마감일 이후의 미션 조회
    List<Mission> findByMissionDeadlineAfterOrderByCreatedAtDesc(LocalDate deadline);
    
    // 5. 특정 조건을 포함하는 미션 조회 (LIKE 검색)
    List<Mission> findByConditionalContainingOrderByCreatedAtDesc(String keyword);
    
    /**
     * @Query 어노테이션을 사용한 JPQL 쿼리들
     */
    
    // 6. 특정 가게의 모든 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.store.storeId = :storeId ORDER BY m.createdAt DESC")
    List<Mission> findByStoreId(@Param("storeId") Long storeId);
    
    // 7. 특정 가게의 특정 점수 이상 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.store.storeId = :storeId AND m.score >= :minScore ORDER BY m.createdAt DESC")
    List<Mission> findByStoreIdAndMinScore(@Param("storeId") Long storeId, @Param("minScore") Integer minScore);
    
    // 8. 미션과 가게 정보를 함께 조회하는 DTO 프로젝션
    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.MissionWithStoreDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "m.missionDeadline, " +
            "s.storeName, " +
            "s.category) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "ORDER BY m.createdAt DESC")
    List<MissionWithStoreDto> findMissionsWithStoreInfo();
    
    // 9. 페이징을 포함한 미션 조회
    @Query("SELECT m FROM Mission m ORDER BY m.createdAt DESC")
    Page<Mission> findAllOrderByCreatedAtDesc(Pageable pageable);
    
    /**
     * 홈 화면 쿼리 - 특정 지역에서 사용자가 아직 도전하지 않은 미션 목록
     * 원본 SQL: SELECT M.mission_id, M.conditional, M.score, S.store_name, S.category, L.location_name, M.mission_deadline
     *           FROM mission AS M
     *           INNER JOIN store AS S ON M.store_id = S.store_id
     *           INNER JOIN location AS L ON S.location_id = L.location_id
     *           LEFT JOIN user_mission AS UM ON M.mission_id = UM.mission_id AND UM.user_id = [사용자 ID]
     *           WHERE L.location_name = '안암동' AND UM.mission_id IS NULL
     *           ORDER BY M.created_at DESC
     *           LIMIT 10 OFFSET 0;
     */
    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.HomeMissionDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "s.storeName, " +
            "s.category, " +
            "l.locationName, " +
            "m.missionDeadline) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "LEFT JOIN UserMission um ON m.missionId = um.mission.missionId AND um.user.userId = :userId " +
            "WHERE l.locationName = :locationName AND um.mission.missionId IS NULL " +
            "ORDER BY m.createdAt DESC")
    Page<HomeMissionDto> findAvailableMissionsByLocation(
            @Param("locationName") String locationName, 
            @Param("userId") Long userId, 
            Pageable pageable);
    
    /**
     * 메서드 이름으로 쿼리 생성 방식 - 홈 화면용
     */
    
    // 특정 지역의 모든 미션 조회 (사용자 도전 여부 무관)
    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.HomeMissionDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "s.storeName, " +
            "s.category, " +
            "l.locationName, " +
            "m.missionDeadline) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "WHERE l.locationName = :locationName " +
            "ORDER BY m.createdAt DESC")
    List<HomeMissionDto> findAllMissionsByLocation(@Param("locationName") String locationName);
    
    // 특정 지역의 특정 점수 이상 미션 조회
    @Query("SELECT new com.example.jpa_practice.domain.mission.dto.HomeMissionDto(" +
            "m.missionId, " +
            "m.conditional, " +
            "m.score, " +
            "s.storeName, " +
            "s.category, " +
            "l.locationName, " +
            "m.missionDeadline) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "LEFT JOIN UserMission um ON m.missionId = um.mission.missionId AND um.user.userId = :userId " +
            "WHERE l.locationName = :locationName AND m.score >= :minScore AND um.mission.missionId IS NULL " +
            "ORDER BY m.createdAt DESC")
    List<HomeMissionDto> findAvailableMissionsByLocationAndMinScore(
            @Param("locationName") String locationName, 
            @Param("userId") Long userId, 
            @Param("minScore") Integer minScore);
}

package com.example.jpa_practice.domain.mission.controller;

import com.example.jpa_practice.domain.mission.dto.HomeMissionDto;
import com.example.jpa_practice.domain.mission.dto.MissionWithStoreDto;
import com.example.jpa_practice.domain.mission.dto.UserMissionDto;
import com.example.jpa_practice.domain.mission.entity.Mission;
import com.example.jpa_practice.domain.mission.entity.UserMission;
import com.example.jpa_practice.domain.mission.repository.MissionRepository;
import com.example.jpa_practice.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    /**
     * 원본 SQL 쿼리를 JPQL로 리팩토링한 메인 쿼리
     * SELECT M.mission_id, M.conditional, M.score, S.store_name, UM.mission_success
     * FROM user_mission AS UM
     * INNER JOIN mission AS M ON UM.mission_id = M.mission_id
     * INNER JOIN store AS S ON M.store_id = S.store_id
     * WHERE UM.user_id = [사용자 ID]
     * ORDER BY UM.created_at DESC
     * LIMIT 10 OFFSET 0;
     */
    @GetMapping("/user/{userId}")
    public Page<UserMissionDto> getUserMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return userMissionRepository.findUserMissionsByUserId(userId, pageable);
    }

    /**
     * 메서드 이름으로 쿼리 생성 방식들 테스트
     */
    
    @GetMapping("/user/{userId}/all")
    public List<UserMission> getAllUserMissions(@PathVariable Long userId) {
        return userMissionRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
    }

    @GetMapping("/user/{userId}/success")
    public List<UserMission> getSuccessUserMissions(@PathVariable Long userId) {
        return userMissionRepository.findByUserUserIdAndMissionSuccessTrueOrderByCreatedAtDesc(userId);
    }

    @GetMapping("/user/{userId}/failed")
    public List<UserMission> getFailedUserMissions(@PathVariable Long userId) {
        return userMissionRepository.findByUserUserIdAndMissionSuccessFalseOrderByCreatedAtDesc(userId);
    }

    @GetMapping("/user/{userId}/paged")
    public Page<UserMission> getUserMissionsPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return userMissionRepository.findByUserUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    /**
     * Mission 관련 쿼리들 테스트
     */
    
    @GetMapping("/score/{minScore}")
    public List<Mission> getMissionsByMinScore(@PathVariable Integer minScore) {
        return missionRepository.findByScoreGreaterThanEqualOrderByCreatedAtDesc(minScore);
    }

    @GetMapping("/score/between")
    public List<Mission> getMissionsByScoreRange(
            @RequestParam Integer minScore,
            @RequestParam Integer maxScore) {
        return missionRepository.findByScoreBetweenOrderByCreatedAtDesc(minScore, maxScore);
    }

    @GetMapping("/deadline/before/{deadline}")
    public List<Mission> getMissionsBeforeDeadline(@PathVariable String deadline) {
        LocalDate date = LocalDate.parse(deadline);
        return missionRepository.findByMissionDeadlineBeforeOrderByCreatedAtDesc(date);
    }

    @GetMapping("/search")
    public List<Mission> searchMissionsByCondition(@RequestParam String keyword) {
        return missionRepository.findByConditionalContainingOrderByCreatedAtDesc(keyword);
    }

    @GetMapping("/store/{storeId}")
    public List<Mission> getMissionsByStore(@PathVariable Long storeId) {
        return missionRepository.findByStoreId(storeId);
    }

    @GetMapping("/store/{storeId}/score/{minScore}")
    public List<Mission> getMissionsByStoreAndMinScore(
            @PathVariable Long storeId,
            @PathVariable Integer minScore) {
        return missionRepository.findByStoreIdAndMinScore(storeId, minScore);
    }

    @GetMapping("/with-store-info")
    public List<MissionWithStoreDto> getMissionsWithStoreInfo() {
        return missionRepository.findMissionsWithStoreInfo();
    }

    @GetMapping("/paged")
    public Page<Mission> getAllMissionsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAllOrderByCreatedAtDesc(pageable);
    }

    /**
     * 홈 화면 쿼리들 - 특정 지역에서 사용자가 아직 도전하지 않은 미션 목록
     * 원본 SQL: SELECT M.mission_id, M.conditional, M.score, S.store_name, S.category, L.location_name, M.mission_deadline
     *           FROM mission AS M
     *           INNER JOIN store AS S ON M.store_id = S.store_id
     *           INNER JOIN location AS L ON S.location_id = L.location_id
     *           LEFT JOIN user_mission AS UM ON M.mission_id = UM.mission_id AND UM.user_id = [사용자 ID]
     *           WHERE L.location_name = '안암동' AND UM.mission_id IS NULL
     *           ORDER BY M.created_at DESC
     *           LIMIT 10 OFFSET 0;
     */
    
    @GetMapping("/home/{locationName}/user/{userId}")
    public Page<HomeMissionDto> getAvailableMissionsByLocation(
            @PathVariable String locationName,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAvailableMissionsByLocation(locationName, userId, pageable);
    }

    @GetMapping("/home/{locationName}/all")
    public List<HomeMissionDto> getAllMissionsByLocation(@PathVariable String locationName) {
        return missionRepository.findAllMissionsByLocation(locationName);
    }

    @GetMapping("/home/{locationName}/user/{userId}/score/{minScore}")
    public List<HomeMissionDto> getAvailableMissionsByLocationAndMinScore(
            @PathVariable String locationName,
            @PathVariable Long userId,
            @PathVariable Integer minScore) {
        
        return missionRepository.findAvailableMissionsByLocationAndMinScore(locationName, userId, minScore);
    }
}

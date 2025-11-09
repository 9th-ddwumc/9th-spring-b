package com.example.jpa_practice.domain.mission.controller;

import com.example.jpa_practice.domain.mission.dto.HomeMissionDto;
import com.example.jpa_practice.domain.mission.dto.MissionWithStoreDto;
import com.example.jpa_practice.domain.mission.dto.UserMissionDto;
import com.example.jpa_practice.domain.mission.entity.Mission;
import com.example.jpa_practice.domain.mission.entity.UserMission;
import com.example.jpa_practice.domain.mission.repository.MissionRepository;
import com.example.jpa_practice.domain.mission.repository.UserMissionRepository;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralSuccessCode;
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
    public ApiResponse<Page<UserMissionDto>> getUserMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserMissionDto> missions = userMissionRepository.findUserMissionsByUserId(userId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    /**
     * 메서드 이름으로 쿼리 생성 방식들 테스트
     */
    
    @GetMapping("/user/{userId}/all")
    public ApiResponse<List<UserMission>> getAllUserMissions(@PathVariable Long userId) {
        List<UserMission> missions = userMissionRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/user/{userId}/success")
    public ApiResponse<List<UserMission>> getSuccessUserMissions(@PathVariable Long userId) {
        List<UserMission> missions = userMissionRepository.findByUserUserIdAndMissionSuccessTrueOrderByCreatedAtDesc(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/user/{userId}/failed")
    public ApiResponse<List<UserMission>> getFailedUserMissions(@PathVariable Long userId) {
        List<UserMission> missions = userMissionRepository.findByUserUserIdAndMissionSuccessFalseOrderByCreatedAtDesc(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/user/{userId}/paged")
    public ApiResponse<Page<UserMission>> getUserMissionsPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserMission> missions = userMissionRepository.findByUserUserIdOrderByCreatedAtDesc(userId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    /**
     * Mission 관련 쿼리들 테스트
     */
    
    @GetMapping("/score/{minScore}")
    public ApiResponse<List<Mission>> getMissionsByMinScore(@PathVariable Integer minScore) {
        List<Mission> missions = missionRepository.findByScoreGreaterThanEqualOrderByCreatedAtDesc(minScore);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/score/between")
    public ApiResponse<List<Mission>> getMissionsByScoreRange(
            @RequestParam Integer minScore,
            @RequestParam Integer maxScore) {
        List<Mission> missions = missionRepository.findByScoreBetweenOrderByCreatedAtDesc(minScore, maxScore);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/deadline/before/{deadline}")
    public ApiResponse<List<Mission>> getMissionsBeforeDeadline(@PathVariable String deadline) {
        LocalDate date = LocalDate.parse(deadline);
        List<Mission> missions = missionRepository.findByMissionDeadlineBeforeOrderByCreatedAtDesc(date);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/search")
    public ApiResponse<List<Mission>> searchMissionsByCondition(@RequestParam String keyword) {
        List<Mission> missions = missionRepository.findByConditionalContainingOrderByCreatedAtDesc(keyword);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<List<Mission>> getMissionsByStore(@PathVariable Long storeId) {
        List<Mission> missions = missionRepository.findByStoreId(storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/store/{storeId}/score/{minScore}")
    public ApiResponse<List<Mission>> getMissionsByStoreAndMinScore(
            @PathVariable Long storeId,
            @PathVariable Integer minScore) {
        List<Mission> missions = missionRepository.findByStoreIdAndMinScore(storeId, minScore);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/with-store-info")
    public ApiResponse<List<MissionWithStoreDto>> getMissionsWithStoreInfo() {
        List<MissionWithStoreDto> missions = missionRepository.findMissionsWithStoreInfo();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/paged")
    public ApiResponse<Page<Mission>> getAllMissionsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missions = missionRepository.findAllOrderByCreatedAtDesc(pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
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
    public ApiResponse<Page<HomeMissionDto>> getAvailableMissionsByLocation(
            @PathVariable String locationName,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<HomeMissionDto> missions = missionRepository.findAvailableMissionsByLocation(locationName, userId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/home/{locationName}/all")
    public ApiResponse<List<HomeMissionDto>> getAllMissionsByLocation(@PathVariable String locationName) {
        List<HomeMissionDto> missions = missionRepository.findAllMissionsByLocation(locationName);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }

    @GetMapping("/home/{locationName}/user/{userId}/score/{minScore}")
    public ApiResponse<List<HomeMissionDto>> getAvailableMissionsByLocationAndMinScore(
            @PathVariable String locationName,
            @PathVariable Long userId,
            @PathVariable Integer minScore) {
        
        List<HomeMissionDto> missions = missionRepository.findAvailableMissionsByLocationAndMinScore(locationName, userId, minScore);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }
}

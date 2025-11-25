package com.workbook.umc9th1.mission.service;

import com.workbook.umc9th1.mission.dto.MissionResDto;
import jakarta.transaction.Transactional;

public interface MissionQueryService {

    MissionResDto.MissionListResponse findStoreMissions(String storeName, Integer page);

    MissionResDto.MissionListResponse findMyOngoingMissions(Long memberId, Integer page);

    @Transactional
    MissionResDto.MissionListItem completeMission(Long memberId, Long missionId);
}

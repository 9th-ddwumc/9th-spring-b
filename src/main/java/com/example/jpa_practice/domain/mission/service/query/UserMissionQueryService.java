package com.example.jpa_practice.domain.mission.service.query;

import com.example.jpa_practice.domain.mission.dto.UserMissionResDTO;

public interface UserMissionQueryService {

    UserMissionResDTO.UserMissionPreviewListDTO getInProgressMissions(Long userId, int pageIndex);
}


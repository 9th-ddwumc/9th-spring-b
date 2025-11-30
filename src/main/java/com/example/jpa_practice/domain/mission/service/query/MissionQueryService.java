package com.example.jpa_practice.domain.mission.service.query;

import com.example.jpa_practice.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.MissionPreviewListDTO getMissionsByStore(Long storeId, int pageIndex);
}


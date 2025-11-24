package com.example.jpa_practice.domain.mission.service.query;

import com.example.jpa_practice.domain.mission.converter.MissionConverter;
import com.example.jpa_practice.domain.mission.dto.MissionResDTO;
import com.example.jpa_practice.domain.mission.entity.Mission;
import com.example.jpa_practice.domain.mission.repository.MissionRepository;
import com.example.jpa_practice.domain.store.repository.StoreRepository;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.MissionPreviewListDTO getMissionsByStore(Long storeId, int pageIndex) {
        if (storeId == null || storeId <= 0) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "storeId는 1 이상의 값이어야 합니다."
            );
        }

        storeRepository.findById(storeId).orElseThrow(() ->
                new CustomException(GeneralErrorCode.NOT_FOUND, "Store not found: " + storeId));

        PageRequest pageRequest = PageRequest.of(pageIndex, 10);
        Page<Mission> page = missionRepository.findByStoreStoreIdOrderByCreatedAtDesc(storeId, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(page);
    }
}


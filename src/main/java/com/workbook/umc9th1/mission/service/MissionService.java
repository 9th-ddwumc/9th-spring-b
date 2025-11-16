package com.workbook.umc9th1.mission.service;

import com.workbook.umc9th1.mission.domain.Mission;
import com.workbook.umc9th1.mission.dto.MissionCreateReqDto;
import com.workbook.umc9th1.mission.dto.MissionCreateResDto;
import com.workbook.umc9th1.mission.repository.MissionRepository;
import com.workbook.umc9th1.store.domain.Store;
import com.workbook.umc9th1.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionCreateResDto createMission(MissionCreateReqDto request) {

        Store store = storeRepository.findById(request.getStoreId()).get();

        Mission mission = Mission.builder()
                .store(store)
                .content(request.getContent())
                .rewardPoint(request.getRewardPoint())
                .endDay(request.getEndDay())
                .build();

        missionRepository.save(mission);

        return MissionCreateResDto.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .content(mission.getContent())
                .rewardPoint(mission.getRewardPoint())
                .endDay(mission.getEndDay() != null ? mission.getEndDay().toString() : null)
                .build();
    }
}

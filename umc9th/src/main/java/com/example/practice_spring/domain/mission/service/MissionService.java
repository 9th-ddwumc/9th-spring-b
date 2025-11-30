package com.example.practice_spring.domain.mission.service;

import com.example.practice_spring.domain.mission.converter.MissionConverter;
import com.example.practice_spring.domain.mission.dto.req.MissionReqDto;
import com.example.practice_spring.domain.mission.dto.res.MissionResDto;
import com.example.practice_spring.domain.mission.entity.Mission;
import com.example.practice_spring.domain.mission.repository.MissionRepository;
import com.example.practice_spring.domain.store.entity.Store;
import com.example.practice_spring.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public List<MissionResDto> getMissionsByStore(Long storeId) {
        List<Mission> missions = missionRepository.findByStore_StoreId(storeId);
        return MissionConverter.toDtoList(missions);
    }

    public MissionResDto addMission(Long storeId, MissionReqDto reqDto){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));

        // 생성자에서 필드 세팅 완료
        Mission mission = new Mission(
                reqDto.getCondition(),
                reqDto.getPoint(),
                reqDto.getDeadline(),
                store
        );

        // 저장 후 DTO 변환
        return MissionConverter.toDto(missionRepository.save(mission));
    }

}

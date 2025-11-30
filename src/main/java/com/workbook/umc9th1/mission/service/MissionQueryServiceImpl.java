package com.workbook.umc9th1.mission.service;

import com.workbook.umc9th1.global.apiPayload.exception.GeneralException;
import com.workbook.umc9th1.member.domain.mapping.MemberMission;
import com.workbook.umc9th1.member.repository.MemberRepository;
import com.workbook.umc9th1.mission.converter.MissionConverter;
import com.workbook.umc9th1.mission.domain.Mission;
import com.workbook.umc9th1.mission.dto.MissionListItemDto;
import com.workbook.umc9th1.mission.dto.MissionResDto;
import com.workbook.umc9th1.mission.enums.MissionStatus;
import com.workbook.umc9th1.mission.exception.code.MissionErrorCode;
import com.workbook.umc9th1.mission.repository.MemberMissionRepository;
import com.workbook.umc9th1.mission.repository.MissionRepository;
import com.workbook.umc9th1.store.domain.Store;
import com.workbook.umc9th1.store.exception.code.StoreErrorCode;
import com.workbook.umc9th1.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDto.MissionListResponse findStoreMissions(String storeName, Integer page) {

        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new GeneralException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        // 미션은 Projection DTO가 없으므로 직접 mapping
        Page<MissionListItemDto> converted = missionPage.map(
                m -> new MissionListItemDto(
                        null,
                        m.getId(),
                        storeName,
                        m.getContent(),
                        m.getRewardPoint(),
                        m.getEndDay(),
                        null,
                        null,
                        null
                )
        );

        return MissionConverter.toMissionListResponse(converted);
    }

    @Override
    public MissionResDto.MissionListResponse findMyOngoingMissions(Long memberId, Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<MissionListItemDto> missionPage =
                memberMissionRepository.findMyMissionsByStatus(
                        memberId,
                        MissionStatus.IN_PROGRESS,
                        pageRequest
                );

        return MissionConverter.toMissionListResponse(missionPage);
    }

    @Transactional
    @Override
    public MissionResDto.MissionListItem completeMission(Long memberId, Long missionId) {

        MemberMission mm = memberMissionRepository
                .findByMember_IdAndMission_Id(memberId, missionId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        mm.completeMission();

        return MissionConverter.toMissionListItemDto(
                new MissionListItemDto(
                        mm.getId(),
                        mm.getMission().getId(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getContent(),
                        mm.getMission().getRewardPoint(),
                        mm.getMission().getEndDay(),
                        mm.getStatus().name(),
                        mm.getRequestedAt(),
                        mm.getCompletedAt()
                )
        );
    }
}

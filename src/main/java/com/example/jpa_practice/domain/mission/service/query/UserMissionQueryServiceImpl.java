package com.example.jpa_practice.domain.mission.service.query;

import com.example.jpa_practice.domain.mission.converter.UserMissionConverter;
import com.example.jpa_practice.domain.mission.dto.UserMissionDto;
import com.example.jpa_practice.domain.mission.dto.UserMissionResDTO;
import com.example.jpa_practice.domain.mission.repository.UserMissionRepository;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public UserMissionResDTO.UserMissionPreviewListDTO getInProgressMissions(Long userId, int pageIndex) {
        if (userId == null || userId <= 0) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "userId는 1 이상의 값이어야 합니다."
            );
        }

        PageRequest pageRequest = PageRequest.of(pageIndex, 10);
        Page<UserMissionDto> page = userMissionRepository.findInProgressMissionsByUserId(userId, pageRequest);
        return UserMissionConverter.toPreviewList(page);
    }
}


package com.workbook.umc9th1.member.service.command;

import com.workbook.umc9th1.food.domain.Food;
import com.workbook.umc9th1.food.exception.FoodException;
import com.workbook.umc9th1.food.exception.code.FoodErrorCode;
import com.workbook.umc9th1.food.repository.FoodRepository;
import com.workbook.umc9th1.member.converter.MemberConverter;
import com.workbook.umc9th1.member.domain.Member;
import com.workbook.umc9th1.member.domain.mapping.MemberFood;
import com.workbook.umc9th1.member.dto.MemberReqDto;
import com.workbook.umc9th1.member.dto.MemberResDto;
import com.workbook.umc9th1.member.enums.Role;
import com.workbook.umc9th1.member.repository.MemberFoodRepository;
import com.workbook.umc9th1.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    @Override
    public MemberResDto.JoinDto signup(
            MemberReqDto.JoinDto dto
    ){

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

//            // 선호 음식 존재 여부 확인
//            if (dto.preferCategory().size() > 1){
//                List<MemberFood> memberFood = dto.preferCategory().stream()
//                        .map(id -> MemberFood.builder()
//                                .member(member)
//                                .food(foodRepository.findById(id)
//                                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
//                                .build()
//                        )
//                        .collect(Collectors.toList());
//
//                memberFoodRepository.saveAll(memberFood);
//            }



            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);

        }
        // 응답 DTO 생성
        return MemberConverter.toJoinDto(member);
    }
}

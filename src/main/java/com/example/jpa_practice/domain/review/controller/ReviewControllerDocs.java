package com.example.jpa_practice.domain.review.controller;

import com.example.jpa_practice.domain.review.dto.ReviewResDTO;
import com.example.jpa_practice.global.annotation.PositivePage;
import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {

    @Operation(
            summary = "가게 리뷰 목록 API (개발 중)",
            description = "특정 가게의 리뷰를 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewPreViewListDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "리뷰 목록 조회 실패",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorCode.class)
                    )
            )
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @Parameter(description = "가게 이름", example = "맛있는집")
            @RequestParam String storeName,
            @Parameter(description = "0부터 시작하는 페이지 번호", example = "0")
            @RequestParam(defaultValue = "0") int page
    );

    @Operation(
            summary = "내가 작성한 리뷰 목록",
            description = "사용자가 작성한 리뷰를 페이지네이션으로 조회합니다. 페이지는 1부터 시작합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "내 리뷰 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewPreViewListDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorCode.class)
                    )
            )
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviewList(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId,
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @PositivePage int page
    );
}
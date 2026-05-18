package com.likelion.tripTruth.domain.tripgroup.controller;

import com.likelion.tripTruth.domain.tripgroup.dto.request.TripGroupCreateRequestDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupInviteResponseDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupResponseDto;
import com.likelion.tripTruth.domain.tripgroup.service.TripGroupService;
import com.likelion.tripTruth.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip-groups")
@Tag(name = "Trip Group API", description = "여행 그룹 관련 API")
public class TripGroupController {

    private final TripGroupService tripGroupService;

    @Operation(summary = "그룹 생성 API", description = "새로운 여행 그룹을 생성하는 API입니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<TripGroupResponseDto>> createTripGroup(
            @Valid @RequestBody TripGroupCreateRequestDto requestDto
    ) {
        TripGroupResponseDto responseDto = tripGroupService.createTripGroup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(responseDto));
    }

    @GetMapping("/invite/{inviteCode}")
    @Operation(
            summary = "초대 링크 그룹 정보 조회 API",
            description = "발급된 초대 코드를 통해 가입 전 그룹의 기본 정보를 조회합니다." 
    )
    public ResponseEntity<ApiResponse<TripGroupInviteResponseDto>> getTripGroupInviteInfo(
            @Parameter(description = "12자리 초대 코드", example = "a1b2c3d4e5f6")
            @PathVariable("inviteCode") String inviteCode
    ) {
        TripGroupInviteResponseDto responseDto = tripGroupService.getTripGroupInviteInfo(inviteCode);
        return ResponseEntity.ok(ApiResponse.onSuccess(responseDto));
    }
}
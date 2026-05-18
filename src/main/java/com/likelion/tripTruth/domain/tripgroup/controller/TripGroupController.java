package com.likelion.tripTruth.domain.tripgroup.controller;

import com.likelion.tripTruth.domain.tripgroup.dto.request.TripGroupCreateRequestDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupResponseDto;
import com.likelion.tripTruth.domain.tripgroup.service.TripGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip-groups")
public class TripGroupController {

    private final TripGroupService tripGroupService;

    @PostMapping
    public ResponseEntity<TripGroupResponseDto> createTripGroup(
            @Valid @RequestBody TripGroupCreateRequestDto requestDto
    ) {
        TripGroupResponseDto responseDto = tripGroupService.createTripGroup(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
package com.likelion.tripTruth.domain.tripgroup.controller;

import com.likelion.tripTruth.domain.tripgroup.service.TripGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip-groups")
public class TripGroupController {

    private final TripGroupService tripGroupService;

    // TODO: API 엔드포인트 작성
}
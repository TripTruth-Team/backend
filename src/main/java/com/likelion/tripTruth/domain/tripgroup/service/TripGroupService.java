package com.likelion.tripTruth.domain.tripgroup.service;

import com.likelion.tripTruth.domain.tripgroup.repository.TripGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripGroupService {

    private final TripGroupRepository tripGroupRepository;

    // TODO: 비즈니스 로직 작성
}
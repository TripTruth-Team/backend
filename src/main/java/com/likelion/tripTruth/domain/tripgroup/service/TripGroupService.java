package com.likelion.tripTruth.domain.tripgroup.service;

import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.domain.member.enums.MemberRole;
import com.likelion.tripTruth.domain.tripgroup.converter.TripGroupConverter;
import com.likelion.tripTruth.domain.tripgroup.dto.request.TripGroupCreateRequestDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupInviteResponseDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupResponseDto;
import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import com.likelion.tripTruth.domain.tripgroup.repository.TripGroupRepository;
import com.likelion.tripTruth.global.apiPayload.code.status.ErrorStatus;
import com.likelion.tripTruth.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripGroupService {

    private final TripGroupRepository tripGroupRepository;
    private static final int MAX_INVITE_CODE_RETRIES = 5;

    /**
     * 그룹 생성 후 방장 자동 등록
     */
    @Transactional
    public TripGroupResponseDto createTripGroup(TripGroupCreateRequestDto requestDto) {

        String inviteCode = generateUniqueInviteCode();

        TripGroup tripGroup = TripGroupConverter.toTripGroup(requestDto, inviteCode);

        GroupMember leader = GroupMember.builder()
                .tripGroup(tripGroup)
                .nickname(requestDto.getLeaderNickname())
                .role(MemberRole.LEADER)
                .isSurveyCompleted(false)
                .build();

        tripGroup.getMembers().add(leader);

        TripGroup savedTripGroup = tripGroupRepository.save(tripGroup);

        GroupMember savedLeader = savedTripGroup.getMembers().stream()
                .filter(member -> member.getRole() == MemberRole.LEADER)
                .findFirst()
                .orElseThrow(() -> new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR));

        return TripGroupConverter.toTripGroupResponseDto(savedTripGroup, savedLeader);
    }

    /**
     * 초대 코드로 그룹 기본 정보 조회
     */
    public TripGroupInviteResponseDto getTripGroupInviteInfo(String inviteCode) {
        TripGroup tripGroup = tripGroupRepository.findByInviteCode(inviteCode)
                .orElseThrow(() -> new GeneralException(ErrorStatus.INVALID_INVITE_CODE));

        return TripGroupConverter.toTripGroupInviteResponseDto(tripGroup);
    }

    /**
     * UUID 초대 코드를 생성 메서드
     */
    private String generateUniqueInviteCode() {
        for (int i = 0; i < MAX_INVITE_CODE_RETRIES; i++) {
            String inviteCode = UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 12);

            if (!tripGroupRepository.existsByInviteCode(inviteCode)) {
                return inviteCode;
            }
        }
        throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
    }
}
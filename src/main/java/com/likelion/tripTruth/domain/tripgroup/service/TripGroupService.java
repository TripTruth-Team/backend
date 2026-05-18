package com.likelion.tripTruth.domain.tripgroup.service;

import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.domain.member.enums.MemberRole;
import com.likelion.tripTruth.domain.tripgroup.dto.request.TripGroupCreateRequestDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupResponseDto;
import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import com.likelion.tripTruth.domain.tripgroup.enums.GroupStatus;
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
     * 새로운 그룹 생성 후 방장을 자동 등록
     */
    @Transactional
    public TripGroupResponseDto createTripGroup(TripGroupCreateRequestDto requestDto) {

        String inviteCode = generateUniqueInviteCode();

        TripGroup tripGroup = TripGroup.builder()
                .name(requestDto.getName())
                .tripLength(requestDto.getTripLength())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .inviteCode(inviteCode)
                .status(GroupStatus.GATHERING)
                .build();

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

        return TripGroupResponseDto.builder()
                .tripGroupId(savedTripGroup.getId())
                .name(savedTripGroup.getName())
                .tripLength(savedTripGroup.getTripLength())
                .startDate(savedTripGroup.getStartDate())
                .endDate(savedTripGroup.getEndDate())
                .inviteCode(savedTripGroup.getInviteCode())
                .status(savedTripGroup.getStatus())
                .leaderMemberId(savedLeader.getId())
                .leaderNickname(savedLeader.getNickname())
                .build();
    }

    /**
     * UUID 초대 코드를 생성, DB 중복 검사 후 12자리 코드 반환
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
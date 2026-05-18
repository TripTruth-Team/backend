package com.likelion.tripTruth.domain.tripgroup.converter;

import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.domain.tripgroup.dto.request.TripGroupCreateRequestDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupInviteResponseDto;
import com.likelion.tripTruth.domain.tripgroup.dto.response.TripGroupResponseDto;
import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import com.likelion.tripTruth.domain.tripgroup.enums.GroupStatus;

public class TripGroupConverter {

    /**
     * 그룹 생성
     */
    public static TripGroup toTripGroup(TripGroupCreateRequestDto requestDto, String inviteCode) {
        return TripGroup.builder()
                .name(requestDto.getName())
                .tripLength(requestDto.getTripLength())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .inviteCode(inviteCode)
                .status(GroupStatus.GATHERING)
                .build();
    }

    /**
     * TripGroup 엔티티 + 방장 정보 -> 그룹 생성 DTO 변환
     */
    public static TripGroupResponseDto toTripGroupResponseDto(TripGroup tripGroup, GroupMember leader) {
        return TripGroupResponseDto.builder()
                .tripGroupId(tripGroup.getId())
                .name(tripGroup.getName())
                .tripLength(tripGroup.getTripLength())
                .startDate(tripGroup.getStartDate())
                .endDate(tripGroup.getEndDate())
                .inviteCode(tripGroup.getInviteCode())
                .status(tripGroup.getStatus())
                .leaderMemberId(leader.getId())
                .leaderNickname(leader.getNickname())
                .build();
    }

    /**
     * TripGroup 엔티티 -> 초대 링크 정보 조회 DTO 변환
     */
    public static TripGroupInviteResponseDto toTripGroupInviteResponseDto(TripGroup tripGroup) {
        return TripGroupInviteResponseDto.builder()
                .name(tripGroup.getName())
                .tripLength(tripGroup.getTripLength())
                .startDate(tripGroup.getStartDate())
                .endDate(tripGroup.getEndDate())
                .build();
    }
}
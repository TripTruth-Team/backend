package com.likelion.tripTruth.domain.tripgroup.dto.response;

import com.likelion.tripTruth.domain.tripgroup.enums.GroupStatus;
import com.likelion.tripTruth.domain.tripgroup.enums.TripLength;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TripGroupResponseDto {
    private Long tripGroupId;
    private String name;
    private TripLength tripLength;
    private LocalDate startDate;
    private LocalDate endDate;
    private String inviteCode;
    private GroupStatus status;
    private Long leaderMemberId;
    private String leaderNickname;
}
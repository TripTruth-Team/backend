package com.likelion.tripTruth.domain.tripgroup.dto.response;

import com.likelion.tripTruth.domain.tripgroup.enums.TripLength;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TripGroupInviteResponseDto {
    private String name;
    private TripLength tripLength;
    private LocalDate startDate;
    private LocalDate endDate;
}
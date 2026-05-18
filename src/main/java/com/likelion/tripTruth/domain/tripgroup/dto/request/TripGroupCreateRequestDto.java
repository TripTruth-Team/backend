package com.likelion.tripTruth.domain.tripgroup.dto.request;

import com.likelion.tripTruth.domain.tripgroup.enums.TripLength;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TripGroupCreateRequestDto {

    @NotBlank(message = "그룹 이름은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "그룹 이름은 한글 2~10자만 입력 가능하며 영문, 숫자, 공백, 특수문자는 불가능합니다.")
    private String name;

    @NotNull(message = "여행 길이는 필수 선택 항목입니다.")
    private TripLength tripLength;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank(message = "방장 닉네임은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,10}$", message = "닉네임은 한글/영문 2~10자만 입력 가능하며 숫자, 공백, 특수문자는 불가능합니다.")
    private String leaderNickname;
}
package com.likelion.tripTruth.global.apiPayload.code.status;

import com.likelion.tripTruth.global.apiPayload.code.BaseCode;
import com.likelion.tripTruth.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // 기본 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 그룹 에러
    GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "GROUP404", "존재하지 않는 그룹입니다."),
    INVALID_INVITE_CODE(HttpStatus.BAD_REQUEST, "GROUP4001", "유효하지 않거나 만료된 초대 코드입니다."),
    GROUP_LEADER_ONLY(HttpStatus.FORBIDDEN, "GROUP4031", "방장만 수행할 수 있는 권한입니다."),
    GROUP_ALREADY_ANALYZING(HttpStatus.BAD_REQUEST, "GROUP4002", "이미 AI 분석이 시작된 그룹입니다."),
    GROUP_NOT_GATHERING_STATE(HttpStatus.BAD_REQUEST, "GROUP4003", "현재 멤버를 모집하고 설문을 받는 상태가 아닙니다."),

    // 멤버 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 그룹에 존재하지 않는 멤버입니다."),
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "MEMBER409", "그룹 내에 이미 동일한 닉네임이 존재합니다."), // 동명이인 방지용

    // 설문 에러
    SURVEY_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "SURVEY4001", "이미 설문을 완료한 멤버입니다."),
    SURVEY_NOT_ALL_COMPLETED(HttpStatus.BAD_REQUEST, "SURVEY4002", "모든 멤버가 설문을 완료해야 AI 분석을 시작할 수 있습니다."),
    SURVEY_NOT_FOUND(HttpStatus.NOT_FOUND, "SURVEY404", "작성된 설문 데이터가 존재하지 않습니다."),

    // AI 분석 및 플랜 에러
    AI_ANALYSIS_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "AI5001", "AI 분석 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    AI_ANALYSIS_IN_PROGRESS(HttpStatus.BAD_REQUEST, "AI4001", "현재 AI가 분석을 진행 중입니다."),
    PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN404", "존재하지 않는 추천 플랜입니다."),
    PLAN_NOT_VOTING_STATE(HttpStatus.BAD_REQUEST, "PLAN4001", "현재 투표를 진행할 수 있는 상태가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
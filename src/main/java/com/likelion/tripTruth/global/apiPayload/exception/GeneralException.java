package com.likelion.tripTruth.global.apiPayload.exception;

import com.likelion.tripTruth.global.apiPayload.code.BaseCode;
import com.likelion.tripTruth.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseCode code;

    public ReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
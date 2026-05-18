package com.likelion.tripTruth.global.apiPayload.exception.handler;

import com.likelion.tripTruth.global.apiPayload.code.BaseCode;
import com.likelion.tripTruth.global.apiPayload.exception.GeneralException;

public class CustomException extends GeneralException {
    public CustomException(BaseCode errorCode) {
        super(errorCode);
    }
}


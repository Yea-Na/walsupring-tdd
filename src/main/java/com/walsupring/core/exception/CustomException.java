package com.walsupring.core.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    public CustomException(ErrorCode errorCode) {
        //순서 지켜서(상속)
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

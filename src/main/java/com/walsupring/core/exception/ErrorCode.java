package com.walsupring.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS("S000","success"),
    SYSTEM_ERROR("E000","system error"),
    NOT_FOUND_USER("F001","not found user"),
    NOT_FOUND_ADMIN("F002","not found admin"),
    REQUIRE_PARAMETER("F003","require parameter is not be null"),
    INVALID_STATE("F004","invalid state"),
    NOT_FOUND_ELEMENT("F005","not found element");


    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

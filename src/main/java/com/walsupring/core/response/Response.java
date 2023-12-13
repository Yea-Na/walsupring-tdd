package com.walsupring.core.response;

import com.walsupring.core.exception.ErrorCode;

public class Response {
    private Response() {    }
    public static ApiResult<?> ok() {
        return ApiResult.of(ErrorCode.SUCCESS);
    }
    public static ApiResult<?> created() {
        return ApiResult.of(ErrorCode.SUCCESS);
    }
    public static <T> ApiResult<T> created(T data) {
        return ApiResult.of(ErrorCode.SUCCESS,data);
    }
    public  static <T> ApiResult<T> ok(T data) {
        return ApiResult.of(ErrorCode.SUCCESS,data);
    }

    public static <T> ApiResult<T> changed(T data) {
        return ApiResult.of(ErrorCode.SUCCESS,data);
    }
}

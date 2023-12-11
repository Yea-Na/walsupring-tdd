package com.walsupring.core.response;

import com.walsupring.core.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResult<T> {
    private String code;
    private String message;
    private T data;  //제네릭

    //팩토리 메소드 패턴 -> 의미있는 이름을 부여해서 미리 생성해 두는것
    //생성을 위한 이름의 관례 = of
    public static ApiResult<?> of(ErrorCode errorCode) {
        return ApiResult.of(errorCode, null);
    }

    //빌더패턴 <-> 생성자
    public static <T> ApiResult<T> of(ErrorCode errorCode, T data) {
        return ApiResult.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(data)
                .build();

    }

}

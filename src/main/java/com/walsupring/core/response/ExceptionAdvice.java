package com.walsupring.core.response;

import com.walsupring.core.exception.CustomException;
import com.walsupring.core.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j //로깅 어노테이션
@RestControllerAdvice
public class ExceptionAdvice {
    //익셉션 터지는거를 Advice에 등록해줘야함

    @ExceptionHandler(Throwable.class)  //핸들링되지 않은 에러가 터졌을 땐 여기서 걸리게 된다
    public ApiResult<?> unHandledException(Throwable throwable) {
        log.info(throwable.getMessage());
        //slack -> 알림오게함

        return ApiResult.of(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ApiResult<?> customException(CustomException e) {
        return ApiResult.of(e.getErrorCode()); // 응답을 총괄, 에러코드에 따라서 응답이 달라지는 경우
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult<?> illegalArgumentException(IllegalArgumentException e) {
        log.info(e.getMessage()); //콘솔에만 찍힘
        return ApiResult.of(ErrorCode.REQUIRE_PARAMETER);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ApiResult<?> illegalStateException(IllegalStateException e) {
        return ApiResult.of(ErrorCode.INVALID_STATE);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ApiResult<?> noSuchElementException(NoSuchElementException e) {
        return ApiResult.of(ErrorCode.NOT_FOUND_ELEMENT);
    }
//    @ExceptionHandler(
//            {
//                    IllegalArgumentException.class,
//                    IndexOutOfBoundsException.class
//            })  // 여러 개를 배열처럼 사용해줄 수도 있음

}

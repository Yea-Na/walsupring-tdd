package com.walsupring.util;

import com.walsupring.core.exception.CustomException;
import com.walsupring.core.exception.ErrorCode;

public class Preconditions {
    private Preconditions() {}

    public static void require(boolean expression) {
        if(!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void require(boolean expression, Object message) {
        if(!expression) {
            throw new IllegalArgumentException(String.valueOf(message));
        }
    }

    public static void check(boolean expression) {
        if(!expression) {
            throw new IllegalStateException();
        }
    }

    public static void check(boolean expression, Object message) {
        if(!expression) {
            throw new IllegalStateException(String.valueOf(message));
        }
    }

    public static void validate(boolean expression, ErrorCode errorCode) {
        if(!expression) {
            throw new CustomException(errorCode);
        }
    }

}

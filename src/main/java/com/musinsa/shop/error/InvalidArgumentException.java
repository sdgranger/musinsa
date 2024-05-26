package com.musinsa.shop.error;

public class InvalidArgumentException extends BusinessException {

    public InvalidArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidArgumentException() {
        super(ErrorCode.INVALID_ARGUMENT);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

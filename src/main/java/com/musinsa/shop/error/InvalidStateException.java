package com.musinsa.shop.error;

public class InvalidStateException extends BusinessException {

    public InvalidStateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidStateException() {
        super(ErrorCode.INVALID_STATE);
    }

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

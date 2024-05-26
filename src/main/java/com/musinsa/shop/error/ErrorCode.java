package com.musinsa.shop.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_ERROR(500, "500", "에러가 발생했습니다."),
    NOT_FOUND(400, "400", "찾을 수 없습니다."),
    INVALID_ARGUMENT(400, "400", "유효하지 않은 argument 입니다."),
    INVALID_STATE(400, "400", "잘못된 상태입니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

}

package com.musinsa.shop.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private List<FieldErrorResponse> errors;
    private String code;

    private ErrorResponse(ErrorCode exceptionCode) {
        this.message = exceptionCode.getMessage();
        this.status = exceptionCode.getStatus();
        this.errors = new ArrayList<>();
        this.code = exceptionCode.getCode();
    }

    private ErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(String message, HttpStatus httpStatus, List<FieldErrorResponse> errors) {
        this.message = message;
        this.status = httpStatus.value();
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorCode exceptionCode) {
        return new ErrorResponse(exceptionCode);
    }

    public static ErrorResponse of(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus);
    }

    public static ErrorResponse of(String message, HttpStatus httpStatus, BindingResult bindingResult) {
        return new ErrorResponse(message, httpStatus, FieldErrorResponse.of(bindingResult));
    }

    public static ErrorResponse of(String message, HttpStatus httpStatus, List<FieldErrorResponse> errors) {
        return new ErrorResponse(message, httpStatus, errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldErrorResponse {
        private String field;
        private String value;
        private String reason;

        private FieldErrorResponse(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldErrorResponse> of(String field, String value, String reason) {
            List<FieldErrorResponse> fieldErrorResponses = new ArrayList<>();
            fieldErrorResponses.add(new FieldErrorResponse(field, value, reason));
            return fieldErrorResponses;
        }

        private static List<FieldErrorResponse> of(BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                .map(error -> new FieldErrorResponse(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()))
                .collect(Collectors.toList());
        }
    }
}

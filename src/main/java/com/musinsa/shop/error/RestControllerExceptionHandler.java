package com.musinsa.shop.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@RestControllerAdvice(
        annotations = RestController.class,
        basePackages = "com.musinsa.shop")
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<?> httpException(Exception ex, HttpServletRequest request) {
        loggingError(ex, request);
        ErrorResponse errorResponse = ErrorResponse.of("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> unknownException(RuntimeException ex, HttpServletRequest request) {
        loggingErrorWithStackTrace(ex, request);
        String message = "에러가 발생했습니다.";
        ErrorResponse errorResponse = ErrorResponse.of(message, HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<?> handleBusinessResultException(BusinessException ex, HttpServletRequest request) {
        loggingErrorWithStackTrace(ex, request);
        ErrorCode exceptionCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(exceptionCode);
        return ResponseEntity
                .status(HttpStatus.valueOf(exceptionCode.getStatus()))
                .body(errorResponse);
    }

    private void loggingErrorWithStackTrace(Exception ex, HttpServletRequest request) {
        String queryString = getQueryString(request);
        log.error("[error] url : {} {} message : {} ", request.getMethod(), request.getRequestURI() + queryString, ex.getMessage(), ex);
    }

    private static String getQueryString(HttpServletRequest request) {
        return request.getQueryString() == null ? "" : "/" + request.getQueryString();
    }

    private void loggingError(Exception ex, HttpServletRequest request) {
        String queryString = getQueryString(request);
        log.error("[error] url : {} {}, message : {} ", request.getMethod(), request.getRequestURI() + queryString, ex.getMessage());
    }

}

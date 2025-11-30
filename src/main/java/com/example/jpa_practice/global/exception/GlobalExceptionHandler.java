package com.example.jpa_practice.global.exception;

import com.example.jpa_practice.global.apiPayload.ApiResponse;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        logWarn(ex);
        return ApiResponse.failure(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        logWarn(ex);
        return ApiResponse.failure(
                GeneralErrorCode.UNAUTHORIZED,
                ex.getMessage()
        );
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleValidationException(Exception ex) {
        logWarn(ex);
        return ApiResponse.failure(
                GeneralErrorCode.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        logWarn(ex);
        return ApiResponse.failure(
                GeneralErrorCode.METHOD_NOT_ALLOWED,
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex) {
        logError(ex);
        return ApiResponse.failure(GeneralErrorCode.INTERNAL_ERROR);
    }

    private void logWarn(Exception ex) {
        log.warn("Handled exception: {}", ex.getMessage());
    }

    private void logError(Exception ex) {
        log.error("Unexpected exception", ex);
    }
}


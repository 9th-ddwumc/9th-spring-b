package com.example.jpa_practice.global.apiPayload;

import com.example.jpa_practice.global.apiPayload.code.BaseErrorCode;
import com.example.jpa_practice.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean success;
    private final String code;
    private final String message;
    private final T result;

    private ApiResponse(boolean success, String code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return new ApiResponse<>(true, successCode.getCode(), successCode.getMessage(), result);
    }

    public static ApiResponse<Void> onSuccess(BaseSuccessCode successCode) {
        return new ApiResponse<>(true, successCode.getCode(), successCode.getMessage(), null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(BaseSuccessCode successCode, T result) {
        return ResponseEntity.status(successCode.getStatus())
                .body(onSuccess(successCode, result));
    }

    public static ResponseEntity<ApiResponse<Void>> success(BaseSuccessCode successCode) {
        return ResponseEntity.status(successCode.getStatus())
                .body(onSuccess(successCode));
    }

    public static ApiResponse<Void> onFailure(BaseErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static ApiResponse<Void> onFailure(BaseErrorCode errorCode, String customMessage) {
        return new ApiResponse<>(false, errorCode.getCode(), customMessage, null);
    }

    public static ResponseEntity<ApiResponse<Void>> failure(BaseErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(onFailure(errorCode));
    }

    public static ResponseEntity<ApiResponse<Void>> failure(BaseErrorCode errorCode, String customMessage) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(onFailure(errorCode, customMessage));
    }
}


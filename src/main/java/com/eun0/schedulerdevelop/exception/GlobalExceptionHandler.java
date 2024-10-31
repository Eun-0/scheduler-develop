package com.eun0.schedulerdevelop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<ApiErrorResponse> handleApplicationException(ApplicationException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse responseBody = new ApiErrorResponse(errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(responseBody);
    }
}
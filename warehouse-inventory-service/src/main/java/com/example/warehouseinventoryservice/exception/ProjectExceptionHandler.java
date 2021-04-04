package com.example.warehouseinventoryservice.exception;

import com.example.warehouseinventoryservice.dto.ErrorDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

/**
 * The type Project exception handler.
 */
@ControllerAdvice
public class ProjectExceptionHandler {

    /**
     * Handle validation exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetails> handleValidationException(Exception exception) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                    .body(ErrorDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .message("ValidationException")
                        .details(exception.getMessage())
                        .build());
    }
}

package com.muez.user.services.exceptions;

import com.muez.user.services.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ApiResponse response = new ApiResponse.Builder()
                .message("Success")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}

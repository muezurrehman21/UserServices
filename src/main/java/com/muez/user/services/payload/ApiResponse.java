package com.muez.user.services.payload;

import org.aspectj.internal.lang.annotation.ajcDeclareEoW;
import org.springframework.http.HttpStatus;


public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiResponse() {
    }

    public ApiResponse(String message, boolean success, HttpStatus httpStatus) {
        this.message = message;
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static class Builder {
        private String message;
        private boolean success;
        private HttpStatus httpStatus;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public ApiResponse build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.message = this.message;
            apiResponse.success = this.success;
            apiResponse.httpStatus = this.httpStatus;
            return apiResponse;
        }

    }
    ApiResponse response = new ApiResponse.Builder()
            .message("Success")
            .success(true)
            .httpStatus(HttpStatus.OK)
            .build();


}

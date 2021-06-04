package com.example.plannerREST.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends Throwable {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}

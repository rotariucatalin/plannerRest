package com.example.plannerREST.exception;

import com.example.plannerREST.entities.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException apiRequestException) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        CustomException exception = new CustomException(apiRequestException.getMessage(), badRequest);

        return new ResponseEntity<>(exception, badRequest);

    }
}

package com.example.plannerREST.exception;

import com.example.plannerREST.entities.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ControllerAdvice
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(httpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<Object> commence(AccessDeniedException accessDeniedException) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        CustomException exception = new CustomException(accessDeniedException.getMessage(), badRequest);

        return new ResponseEntity<>(exception, badRequest);
    }
}

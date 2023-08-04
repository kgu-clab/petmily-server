package com.clab.securecoding.handler;

import com.clab.securecoding.type.dto.ResponseModel;
import com.google.gson.stream.MalformedJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "com.clab.securecoding.controller")
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({
            NoSuchElementException.class,
            MissingServletRequestParameterException.class,
            MalformedJsonException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            NullPointerException.class,
            DataIntegrityViolationException.class
    })
    public ResponseModel parameterError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(400);
        return responseModel;
    }

    @ExceptionHandler({
            AccessDeniedException.class,
    })
    public ResponseModel unAuthorizeRequestError(HttpServletRequest request, HttpServletResponse response,
                                                 Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(401);
        return responseModel;
    }

    @ExceptionHandler({
            NotFoundException.class,
            Exception.class
    })
    public ResponseModel unExceptedError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(500);
        return responseModel;
    }

}
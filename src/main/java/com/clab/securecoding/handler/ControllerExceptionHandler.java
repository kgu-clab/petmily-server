package com.clab.securecoding.handler;

import com.clab.securecoding.exception.*;
import com.clab.securecoding.type.dto.ResponseModel;
import com.google.gson.stream.MalformedJsonException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
            PermissionDeniedException.class,
            ExpiredJwtException.class
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
            SearchResultNotExistException.class
    })
    public ResponseModel searchResultNotExistError(HttpServletRequest request, HttpServletResponse response,
                                                   Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(404);
        return responseModel;
    }

    @ExceptionHandler({
            AssociatedAccountExistsException.class
    })
    public ResponseModel AssociatedAccountExistsExceptionError(HttpServletRequest request, HttpServletResponse response,
                                                   Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(404);
        return responseModel;
    }

    @ExceptionHandler({
            ApiRequestFailedException.class
    })
    public ResponseModel ApiRequestFailedExceptionError(HttpServletRequest request, HttpServletResponse response,
                                                               Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(404);
        return responseModel;
    }

    @ExceptionHandler({
            NotFoundException.class,
            Exception.class,
            SchedulerException.class
    })
    public ResponseModel unExceptedError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(500);
        return responseModel;
    }

}
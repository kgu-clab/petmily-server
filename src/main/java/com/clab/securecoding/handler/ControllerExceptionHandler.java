package com.clab.securecoding.handler;

import com.clab.securecoding.exception.*;
import com.clab.securecoding.repository.UserRepository;
import com.clab.securecoding.service.ErrorDetectAdvisorService;
import com.clab.securecoding.service.LogInfoService;
import com.clab.securecoding.type.dto.LogInfoRequestDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import com.google.gson.stream.MalformedJsonException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestControllerAdvice(basePackages = "com.clab.securecoding.controller")
@Slf4j
public class ControllerExceptionHandler {

    @Autowired
    private ErrorDetectAdvisorService errorDetectAdvisorService;

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler({
            NoSuchElementException.class,
            MissingServletRequestParameterException.class,
            MalformedJsonException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            NullPointerException.class,
            DataIntegrityViolationException.class,
            IllegalArgumentException.class,
            FileUploadFailException.class
    })
    public ResponseModel parameterError(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(400);

        createLogInfoInExceptionHandler(request, LogType.PARAMETER_ERROR, "mid");
        return responseModel;
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            PermissionDeniedException.class,
            ExpiredJwtException.class,
            SecurityException.class,
            MalformedJwtException.class,
            UnsupportedJwtException.class,
            VerificationFailedException.class,
    })
    public ResponseModel unAuthorizeRequestError(HttpServletRequest request, HttpServletResponse response,
                                                 Exception e) throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(401);

        createLogInfoInExceptionHandler(request, LogType.UN_AUTHORIZE_REQUEST_ERROR, "mid");
        return responseModel;
    }

    @ExceptionHandler({
            LoginFaliedException.class,
            UserLockedException.class,
            BadCredentialsException.class
    })
    public ResponseModel LoginFailedError(HttpServletRequest request, HttpServletResponse response,
                                                 Exception e) throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(403);

        createLogInfoInExceptionHandler(request, LogType.LOGIN_FAILED_ERROR, "mid");
        return responseModel;
    }

    @ExceptionHandler({
            SearchResultNotExistException.class,
            FileNotFoundException.class
    })
    public ResponseModel searchResultNotExistError(HttpServletRequest request, HttpServletResponse response,
                                                   Exception e)  throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(404);

        createLogInfoInExceptionHandler(request, LogType.SEARCH_RESULT_NOT_EXIST_ERROR, "mid");
        return responseModel;
    }

    @ExceptionHandler({
            AssociatedAccountExistsException.class,
            DuplicateContactException.class
    })
    public ResponseModel AssociatedAccountExistsExceptionError(HttpServletRequest request, HttpServletResponse response,
                                                   Exception e)  throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(200);

        createLogInfoInExceptionHandler(request, LogType.ASSOCIATED_ACCOUNT_EXISTS_EXCEPTION_ERROR, "mid");
        return responseModel;
    }

    @ExceptionHandler({
            ApiRequestFailedException.class
    })
    public ResponseModel ApiRequestFailedExceptionError(HttpServletRequest request, HttpServletResponse response,
                                                               Exception e) throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(404);

        createLogInfoInExceptionHandler(request, LogType.API_REQUEST_FAILED_EXCEPTION_ERROR, "mid");
        return responseModel;
    }



    @ExceptionHandler({
            NotFoundException.class,
            SchedulerException.class,
            ClassCastException.class,
            MessagingException.class,
            MailSendException.class,
            Exception.class
    })
    public ResponseModel unExceptedError(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        errorDetectAdvisorService.handleException(request, e);
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.setStatus(500);

        createLogInfoInExceptionHandler(request, LogType.NOT_FOUND_EXCEPTION, "mid");
        return responseModel;
    }

    private void createLogInfoInExceptionHandler(HttpServletRequest request, LogType logType, String danger) {
        String loginId = (String) request.getAttribute("loginId");
        LogInfoRequestDto logInfoRequestDto;
        if (loginId != null ) {
            Optional<User> user = userRepository.findByUserId(loginId);
            if (user.isPresent()) {
                UserType type = user.get().getType();
                logInfoRequestDto = new LogInfoRequestDto(logType, loginId, type, request.getRemoteAddr(), danger);
            } else{
                logInfoRequestDto = new LogInfoRequestDto(logType, loginId, null, request.getRemoteAddr(), danger);
            }
        } else {
            logInfoRequestDto = new LogInfoRequestDto(logType, null, UserType.SHELTER, request.getRemoteAddr(), danger);
        }
        logInfoService.createLogInfo(logInfoRequestDto, request);
    }

}
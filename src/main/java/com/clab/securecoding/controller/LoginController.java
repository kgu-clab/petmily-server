package com.clab.securecoding.controller;

import com.clab.securecoding.exception.LoginFaliedException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.UserLockedException;
import com.clab.securecoding.service.LoginService;
import com.clab.securecoding.type.dto.RefreshTokenDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.TokenDto;
import com.clab.securecoding.type.dto.TokenInfo;
import com.clab.securecoding.type.dto.UserLoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Login")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "유저 로그인", description = "JWT 인증 로그인")
    @PostMapping()
    public ResponseModel login(
            @RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletRequest request
    ) throws UserLockedException, LoginFaliedException {
        ResponseModel responseModel = ResponseModel.builder().build();
        String id = userLoginRequestDto.getId();
        String password = userLoginRequestDto.getPassword();
        request.setAttribute("loginId",id);
        TokenInfo tokenInfo = loginService.login(id, password, request);
        responseModel.addData(tokenInfo);
        return responseModel;
    }

    @Operation(summary = "유저 토큰 재발급", description = "유저 토큰 재발급")
    @PostMapping("/reissue")
    public ResponseModel reissue(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        TokenInfo tokenInfo = loginService.reissue(refreshTokenDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(tokenInfo);
        return responseModel;
    }

    @Operation(summary = "유저 밴 처리", description = "유저 밴 처리")
    @PostMapping("/ban/{userId}")
    public ResponseModel banUser(
            @PathVariable String userId
    ) throws PermissionDeniedException {
        loginService.banUserById(userId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 밴 해제", description = "유저 밴 해제")
    @PostMapping("/unban/{userId}")
    public ResponseModel unbanUser(
            @PathVariable String userId
    ) throws PermissionDeniedException {
        loginService.unbanUserById(userId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 토큰 권한 검사", description = "유저 토큰 권한 검사")
    @PostMapping("/role")
    public ResponseModel checkTokenRole(
            @RequestBody TokenDto tokenDto
    ) {
        boolean isAdminRole = loginService.checkTokenRole(tokenDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(isAdminRole);
        return responseModel;
    }

}

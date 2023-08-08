package com.clab.securecoding.controller;

import com.clab.securecoding.service.SmsService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.RequestDto;
import com.clab.securecoding.type.dto.SmsResponseDto;


import com.clab.securecoding.type.dto.VerificationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Tag(name = "SMS")
@Slf4j
public class SmsController {

    private final SmsService smsService;

    @Operation(summary = "SMS 발송", description = "NCP SENS를 이용한 SMS 발송")
    @PostMapping("/send")
    public ResponseModel sendSms(
            @RequestBody RequestDto requestDto
    ) {
        SmsResponseDto smsResponseDto = smsService.sendSms(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(smsResponseDto);
        return responseModel;
    }

    @Operation(summary = "인증 코드 발송", description = "인증 코드를 발송하여 사용자를 인증합니다.")
    @PostMapping("/send-verification-code/{recipientPhoneNumber}")
    public ResponseModel sendVerificationCode(
            @PathVariable String recipientPhoneNumber
    ) {
        SmsResponseDto smsResponseDto = smsService.sendVerificationCode(recipientPhoneNumber);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(smsResponseDto);
        return responseModel;
    }

    @Operation(summary = "인증번호 확인", description = "입력한 전화번호와 인증번호가 일치하고 유효한지 확인")
    @PostMapping("/verify")
    public ResponseModel verifyCode(
            @RequestBody VerificationRequestDto verificationRequestDto
    ) {
        smsService.verifyVerificationCode(verificationRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
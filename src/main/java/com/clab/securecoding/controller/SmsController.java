package com.clab.securecoding.controller;

import com.clab.securecoding.service.SmsService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.Request;
import com.clab.securecoding.type.dto.SmsResponse;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
            @RequestBody Request request
    ) {
        SmsResponse smsResponse = smsService.sendSms(request);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(smsResponse);
        return responseModel;
    }

}
package com.clab.securecoding.controller;

import com.clab.securecoding.service.SmsService;
import com.clab.securecoding.type.entity.Request;
import com.clab.securecoding.type.entity.SmsResponse;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/user/sms")
    public ResponseEntity<SmsResponse> test(@RequestBody Request request)
            throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        log.info("sms controller");
        log.info("request.getRecipientPhoneNumber() = {}",request.getRecipientPhoneNumber());
        log.info("request.getContent() = {}",request.getContent());

        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}
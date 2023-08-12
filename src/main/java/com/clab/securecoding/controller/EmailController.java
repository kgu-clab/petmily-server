package com.clab.securecoding.controller;

import com.clab.securecoding.service.EmailService;
import com.clab.securecoding.type.dto.EmailDetails;
import com.clab.securecoding.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@Tag(name = "Email")
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 발송", description = "이메일 발송<br>" +
            "attachment는 무시<br>" +
            "String recipient;<br>" +
            "String msgBody;<br>" +
            "String subject;<br>" +
            "String attachment;<br>")
    @PostMapping("/sendMail")
    public ResponseModel sendMail(
            @RequestBody EmailDetails details
    ) {
        emailService.sendSimpleMail(details);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}

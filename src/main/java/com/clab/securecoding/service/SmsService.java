package com.clab.securecoding.service;

import com.clab.securecoding.exception.ApiRequestFailedException;
import com.clab.securecoding.exception.VerificationFailedException;
import com.clab.securecoding.repository.VerificationCodeRepository;
import com.clab.securecoding.type.dto.MessagesDto;
import com.clab.securecoding.type.dto.RequestDto;
import com.clab.securecoding.type.dto.SmsPhoneNumberDto;
import com.clab.securecoding.type.dto.SmsRequestDto;
import com.clab.securecoding.type.dto.SmsResponseDto;
import com.clab.securecoding.type.dto.VerificationRequestDto;
import com.clab.securecoding.type.entity.VerificationCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SmsService {

    @Value("${sms.service-id}")
    private String serviceId;

    @Value("${sms.access-key}")
    private String accessKey;

    @Value("${sms.secret-key}")
    private String secretKey;

    @Value("${sms.sender-phone}")
    private String senderPhone;

    private final VerificationCodeRepository verificationCodeRepository;

    private final UserService userService;

    public SmsResponseDto sendSms(RequestDto requestDto) {
        try {
            String recipientPhoneNumber = requestDto.getRecipientPhoneNumber();
            String content = userService.removeHyphensFromContact(requestDto.getContent());
            SmsResponseDto smsResponseDto = sendSmsInternal(recipientPhoneNumber, content);
            return smsResponseDto;
        } catch (VerificationFailedException vfEx) {
            throw new ApiRequestFailedException();
        } catch (ApiRequestFailedException apiEx) {
            throw new ApiRequestFailedException();
        } catch (Exception e) {
            throw new ApiRequestFailedException();
        } finally {
        }
    }

    public SmsResponseDto sendVerificationCode(SmsPhoneNumberDto smsPhoneNumberDto) {
        try {
            String recipientPhoneNumber = userService.removeHyphensFromContact(smsPhoneNumberDto.getRecipientPhoneNumber());
            String verificationCode = generateVerificationCode();
            storeVerificationCode(recipientPhoneNumber, verificationCode);
            String contentWithCode = "Your verification code: " + verificationCode;
            SmsResponseDto smsResponseDto = sendSmsInternal(recipientPhoneNumber, contentWithCode);
            return smsResponseDto;
        } catch (VerificationFailedException vfEx) {
            throw new ApiRequestFailedException();
        } catch (ApiRequestFailedException apiEx) {
            throw new ApiRequestFailedException();
        } catch (Exception e) {
            throw new ApiRequestFailedException();
        } finally {
        }
    }

    public void verifyVerificationCode(VerificationRequestDto verificationRequestDto) {
        String recipientPhoneNumber = userService.removeHyphensFromContact(verificationRequestDto.getRecipientPhoneNumber());
        String inputCode = verificationRequestDto.getVerificationCode();
        VerificationCode storedCode = verificationCodeRepository.findByRecipientPhoneNumber(recipientPhoneNumber);
        if (storedCode == null || !isVerificationCodeValid(storedCode, inputCode)) {
            throw new VerificationFailedException("인증번호 확인에 실패하였습니다.");
        }
        else {
            verificationCodeRepository.deleteByRecipientPhoneNumber(recipientPhoneNumber);
        }
    }

    private boolean isVerificationCodeValid(VerificationCode storedCode, String inputCode) {
        String storedCodeStr = storedCode.getCode();
        long expirationTime = storedCode.getExpirationTime();
        long currentTime = System.currentTimeMillis();
        return storedCodeStr.equals(inputCode) && currentTime <= expirationTime;
    }

    public String makeSignature(Long time) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey);
        String message = messageBuilder.toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    private SmsResponseDto sendSmsInternal(String recipientPhoneNumber, String content) {
        try {
            Long time = System.currentTimeMillis();
            List<MessagesDto> messages = new ArrayList<>();
            messages.add(new MessagesDto(recipientPhoneNumber, content));

            SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", senderPhone, content, messages);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(smsRequestDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-ncp-apigw-timestamp", time.toString());
            headers.set("x-ncp-iam-access-key", this.accessKey);
            String sig = makeSignature(time);
            headers.set("x-ncp-apigw-signature-v2", sig);

            HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            SmsResponseDto smsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId + "/messages"), body, SmsResponseDto.class);
            return smsResponseDto;
        } catch (VerificationFailedException vfEx) {
            throw new ApiRequestFailedException();
        } catch (ApiRequestFailedException apiEx) {
            throw new ApiRequestFailedException();
        } catch (Exception e) {
            throw new ApiRequestFailedException();
        } finally {
        }
    }

    public String generateVerificationCode() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeBytes = new byte[6];
        secureRandom.nextBytes(codeBytes);
        return Base64.encodeBase64URLSafeString(codeBytes);
    }

    private void storeVerificationCode(String recipientPhoneNumber, String code) {
        long expirationTime = System.currentTimeMillis() + 180000;

        verificationCodeRepository.deleteByRecipientPhoneNumber(recipientPhoneNumber);

        VerificationCode verificationCode = VerificationCode.builder()
                .recipientPhoneNumber(recipientPhoneNumber)
                .code(code)
                .expirationTime(expirationTime)
                .build();
        verificationCodeRepository.save(verificationCode);
    }
}
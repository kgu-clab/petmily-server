package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.LogInfoService;
import com.clab.securecoding.type.dto.LogInfoRequestDto;
import com.clab.securecoding.type.dto.LogInfoResponseDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.entity.LogInfo;
import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/log-info")
@RequiredArgsConstructor
@Tag(name = "LogInfo")
@Slf4j
public class LogInfoController {

    private final LogInfoService logInfoService;

    @Operation(summary = "로그 ", description = "로그 ")
    @PostMapping()
    public ResponseModel createLog(@RequestBody LogInfoRequestDto logInfoRequestDto, HttpServletRequest request) {
        LogInfo logInfo = logInfoService.createLogInfo(logInfoRequestDto, request);
        log.info("create log {}",logInfo);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "로그 정보", description = "로그 정보")
    @GetMapping()
    public ResponseModel getLogInfos() {
        List<LogInfoResponseDto> logInfoResponseDtos = logInfoService.getLogInfos();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(logInfoResponseDtos);
        return responseModel;
    }

    @Operation(summary = "로그 검색", description = "로그 검색")
    @GetMapping("/search")
    public ResponseModel searchLogInfos(
            @RequestParam(required = false) LogType logType,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) UserType userType,
            @RequestParam(required = false) String ip,
            @RequestParam(required = false) String danger
    ) {
        List<LogInfoResponseDto> logInfoResponseDtos = logInfoService.searchLogInfos(logType, nickname, userType, ip, danger);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(logInfoResponseDtos);
        return responseModel;
    }

    @Operation(summary = "로그 삭제", description = "로그 삭제")
    @DeleteMapping("/{logInfoId}")
    public ResponseModel deleteLogInfo(
            @PathVariable Long logInfoId
    ) throws PermissionDeniedException {
        logInfoService.deleteLogInfo(logInfoId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
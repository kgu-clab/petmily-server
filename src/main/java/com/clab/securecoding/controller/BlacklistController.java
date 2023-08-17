package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.BlacklistService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.entity.BlacklistIp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blacklists")
@RequiredArgsConstructor
@Tag(name = "Blacklist")
@Slf4j
public class BlacklistController {

    private final BlacklistService blacklistService;

    @Operation(summary = "블랙리스트 IP 추가", description = "블랙리스트 IP 추가")
    @PostMapping()
    public ResponseModel addBlacklistedIp(
            @RequestParam String ipAddress
    ) throws PermissionDeniedException {
        blacklistService.addBlacklistedIp(ipAddress);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "블랙리스트 IP 목록 조회", description = "블랙리스트 IP 목록 조회")
    @GetMapping()
    public ResponseModel getBlacklistedIps() throws PermissionDeniedException {
        List<BlacklistIp> blacklistedIps = blacklistService.getBlacklistedIps();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(blacklistedIps);
        return responseModel;
    }

    @Operation(summary = "블랙리스트 IP 제거", description = "블랙리스트 IP 제거")
    @DeleteMapping()
    public ResponseModel removeBlacklistedIp(
            @RequestParam String ipAddress
    ) throws PermissionDeniedException {
        blacklistService.deleteBlacklistedIp(ipAddress);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "블랙리스트 IP 초기화", description = "블랙리스트 IP 초기화")
    @DeleteMapping("/clear")
    public ResponseModel clearBlacklist() throws PermissionDeniedException {
        blacklistService.clearBlacklist();
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}

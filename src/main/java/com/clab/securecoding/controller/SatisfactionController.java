package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.SatisfactionService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.SatisfactionRequestDto;
import com.clab.securecoding.type.dto.SatisfactionResponseDto;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/satisfaction")
@RequiredArgsConstructor
@Tag(name = "Satisfaction")
public class SatisfactionController {

    private final SatisfactionService satisfactionService;

    @Operation(summary = "만족도 조사 생성", description = "만족도 조사 생성")
    @PostMapping()
    public ResponseModel createSatisfaction(
            @RequestBody SatisfactionRequestDto requestDto
    ) {
        satisfactionService.createSatisfaction(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "만족도 조사 조회", description = "만족도 조사 조회")
    @GetMapping()
    public ResponseModel getSatisfactions() {
        List<SatisfactionResponseDto> satisfactionResponseDtos = satisfactionService.getSatisfactions();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(satisfactionResponseDtos);
        return responseModel;
    }

    @Operation(summary = "만족도 조사 검색", description = "만족도 조사 검색")
    @GetMapping("/search")
    public ResponseModel searchSatisfactions(
            @RequestParam(required = false) Long userId
    ) {
        List<SatisfactionResponseDto> satisfactionResponseDtos = satisfactionService.searchSatisfactions(userId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(satisfactionResponseDtos);
        return responseModel;
    }

    @Operation(summary = "만족도 조사 삭제", description = "만족도 조사 삭제")
    @DeleteMapping("/{satisfactionId}")
    public ResponseModel deleteSatisfaction(
            @PathVariable Long satisfactionId
    ) throws PermissionDeniedException {
        satisfactionService.deleteSatisfaction(satisfactionId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}


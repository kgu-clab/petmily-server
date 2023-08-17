package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.SatisfactionService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.SatisfactionRequestDto;
import com.clab.securecoding.type.dto.SatisfactionResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/satisfaction")
@RequiredArgsConstructor
@Tag(name = "Satisfaction")
public class SatisfactionController {

    private final SatisfactionService satisfactionService;

    @PostMapping("/create")
    public ResponseModel createSatisfaction(
            @RequestBody SatisfactionRequestDto requestDto
    ) {
        satisfactionService.createSatisfaction(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @GetMapping("/list")
    public ResponseModel getSatisfactions() {
        List<SatisfactionResponseDto> satisfactionResponseDtos = satisfactionService.getSatisfactions();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(satisfactionResponseDtos);
        return responseModel;
    }

    @GetMapping("/search")
    public ResponseModel searchSatisfactions(
            @RequestParam(required = false) Long animalAdoptionBoardId,
            @RequestParam(required = false) Long userId
    ) {
        List<SatisfactionResponseDto> satisfactionResponseDtos = satisfactionService.searchSatisfactions(animalAdoptionBoardId, userId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(satisfactionResponseDtos);
        return responseModel;
    }

    @DeleteMapping("/delete/{satisfactionId}")
    public ResponseModel deleteSatisfaction(
            @PathVariable Long satisfactionId
    ) throws PermissionDeniedException {
        satisfactionService.deleteSatisfaction(satisfactionId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}


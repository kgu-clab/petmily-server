package com.clab.securecoding.controller;

import com.clab.securecoding.service.AdoptionRequestService;
import com.clab.securecoding.type.dto.AdoptionReserveRequestDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.entity.AdoptionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoption-requests")
@RequiredArgsConstructor
@Tag(name = "AdoptionRequest")
@Slf4j
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    @Operation(summary = "동물 분양 요청", description = "동물 분양 요청<br>" +
            "Long animalAdoptionBoardId;")
    @PostMapping()
    public ResponseModel sendAdoptionRequest(
            @RequestBody AdoptionReserveRequestDto adoptionReserveRequestDto
    ) {
        adoptionRequestService.sendAdoptionRequest(adoptionReserveRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 분양 요청 리스트", description = "동물 분양 요청 리스트")
    @GetMapping()
    public ResponseModel getAdoptionRequest() {
        List<AdoptionRequest> adoptionRequests = adoptionRequestService.getAdoptionRequest();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(adoptionRequests);
        return responseModel;
    }

    @Operation(summary = "동물 분양 요청 승인", description = "동물 분양 요청 승인")
    @PostMapping("/approve")
    public ResponseModel approveAdoptionRequest(
            @RequestParam Long requestId
    ) {
        adoptionRequestService.approveAdoptionRequest(requestId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 분양 요청 삭제", description = "동물 분양 요청 승인")
    @DeleteMapping()
    public ResponseModel deleteAdoptionRequest(
            @RequestParam Long requestId
    ) {
        adoptionRequestService.deleteAdoptionRequest(requestId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}

package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.ContractService;
import com.clab.securecoding.type.dto.*;
import com.clab.securecoding.type.entity.Contract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@Tag(name = "Contract")
@Slf4j
public class ContractController {

    private final ContractService contractService;

    @Operation(summary = "계약서 생성", description = "계약서 생성<br>" +
            "String content;<br>"+
            "AnimalRequestDto animal;<br>" +
            "String additionalProvisions;<br>" +
            "String signature;")
    @PostMapping()
    public ResponseModel createContract(
            @RequestBody ContractRequestDto contractRequestDto
    ) {
        contractService.createContract(contractRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "내 계약서 정보", description = "가장 최근의 내 계약서 정보 1건 조회")
    @GetMapping()
    public ResponseModel getMyContract() {
        Contract contract = contractService.getMyContract();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(contract);
        return responseModel;
    }

}

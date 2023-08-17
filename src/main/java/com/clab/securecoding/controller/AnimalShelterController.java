package com.clab.securecoding.controller;

import com.clab.securecoding.service.AnimalShelterService;
import com.clab.securecoding.type.dto.AnimalShelterDto;
import com.clab.securecoding.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/animal-shelters")
@RequiredArgsConstructor
@Tag(name = "Animal Shelter")
@Slf4j
public class AnimalShelterController {

    private final AnimalShelterService animalShelterService;

    @Operation(summary = "동물보호소 정보 가져오기", description = "공공데이터에 API 요청을 보내 동물보호소 정보를 가져옴")
    @GetMapping()
    public ResponseModel retrieveShelters() throws IOException, ParseException {
        animalShelterService.retrieveShelters();
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물보호소 목록", description = "동물보호소 목록")
    @GetMapping("/list")
    public ResponseModel getAllShelters() {
        List<AnimalShelterDto> animalShelterDtos = animalShelterService.getAllAnimalShelters();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animalShelterDtos);
        return responseModel;
    }

}
package com.clab.securecoding.controller;

import com.clab.securecoding.service.AnimalService;
import com.clab.securecoding.type.dto.*;
import com.clab.securecoding.type.etc.UserType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
@Tag(name = "Animal")
@Slf4j
public class AnimalController {

    private final AnimalService animalService;

    @Operation(summary = "신규 동물 생성", description = "신규 동물 생성<br>" +
            "String species;<br>" +
            "Long age;<br>"+
            "String gender;<br>" +
            "String specialNotes;<br>" +
            "String vaccine;<br>" +
            "Boolean isNeutered;<br>" +
            "String reasonForAdoption;<br>" +
            "String previousHomeEnvironment;<br>" +
            "String likes;<br>" +
            "String dislikes;")
    @PostMapping("/create")
    public ResponseModel createAnimal(
            @RequestBody AnimalRequestDto animalResquestDto
    ) {
        animalService.createAnimal(animalResquestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 정보", description = "동물 정보 조회")
    @GetMapping("/list")
    public ResponseModel getAnimals() {
        List<AnimalResponseDto> animals = animalService.getAnimals();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animals);
        return responseModel;
    }

    @Operation(summary = "동물 검색", description = "유저의 Seq, Type 또는 동물의 종류를 기반으로 검색")
    @GetMapping("/search")
    public ResponseModel searchAnimal(
            @RequestParam(required = false) Long seq,
            @RequestParam(required = false) UserType userType,
            @RequestParam(required = false) String species
    ) {
        List<AnimalResponseDto> animals = animalService.searchAnimal(seq, userType, species);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animals);
        return responseModel;
    }

    @Operation(summary = "동물 정보 수정", description = "본인의 동물 정보 수정<br>" +
            "String species;<br>" +
            "Long age;<br>"+
            "String gender;<br>" +
            "String specialNotes;<br>" +
            "String vaccine;<br>" +
            "Boolean isNeutered;<br>" +
            "String reasonForAdoption;<br>" +
            "String previousHomeEnvironment;<br>" +
            "String likes;<br>" +
            "String dislikes;")
    @PutMapping("/update")
    public ResponseModel updateAnimalInfoByUser(
            @RequestParam Long animalId,
            @RequestBody AnimalRequestDto animalRequestDto
    ) {
        animalService.updateAnimalInfoByUser(animalId, animalRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 삭제", description = "동물 삭제")
    @DeleteMapping("/delete/{animalId}")
    public ResponseModel deleteAnimal(
            @PathVariable("animalId") Long animalId
    ) {
        animalService.deleteAnimal(animalId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}

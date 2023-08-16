package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.AnimalAdoptionBoardService;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardRequestDto;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardResponseDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.etc.AnimalType;
import com.clab.securecoding.type.etc.UserType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/adoption-boards")
@RequiredArgsConstructor
@Tag(name = "AnimalAdoptionBoard")
public class AnimalAdoptionBoardController {

    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    @Operation(summary = "동물 분양 게시글 생성", description = "동물 분양 게시글 생성<br>" +
            "AnimalType: DOG | CAT | BIRD | FISH | SMALL_ANIMAL | REPTILE<br>" +
            "String species;<br>" +
            "String name;<br>" +
            "String color;<br>" +
            "String gender;<br>" +
            "Long age;<br>" +
            "Double weight;<br>" +
            "String vaccine;<br>" +
            "String isNeutered;<br>" +
            "String birthDay;<br>" +
            "String favoriteSnack;<br>" +
            "String reasonForAdoption;<br>" +
            "String areasAvailable;<br>" +
            "Integer price;<br>" +
            "Double leadership;<br>" +
            "Double independence;<br>" +
            "Double initiative;<br>" +
            "Double positivity;<br>" +
            "Double adaptability;<br>" +
            "String recommendation;<br>" +
            "String think;<br> +" +
            "String imgUrl;")
    @PostMapping()
    public ResponseModel createAnimalAdoptionBoard(
            @RequestBody AnimalAdoptionBoardRequestDto requestDto
    ) {
        animalAdoptionBoardService.createAnimalAdoptionBoard(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 분양 게시글 정보", description = "동물 분양 게시글 정보 조회")
    @GetMapping()
    public ResponseModel getAnimalAdoptionBoards() {
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoards = animalAdoptionBoardService.getAnimalAdoptionBoards();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animalAdoptionBoards);
        return responseModel;
    }

    @Operation(summary = "동물 분양 게시글 상세 정보", description = "동물 분양 게시글 상세 정보 조회")
    @GetMapping("/info")
    public ResponseModel getAnimalAdoptionBoard(
            @RequestParam Long boardId
    ) {
        AnimalAdoptionBoardResponseDto animalAdoptionBoards = animalAdoptionBoardService.getAnimalAdoptionBoard(boardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animalAdoptionBoards);
        return responseModel;
    }

    @Operation(summary = "동물 분양 게시글 검색", description = "유저 타입, 동물 타입, 종, 유저 이름으로 검색 가능")
    @GetMapping("/search")
    public ResponseModel searchAnimalAdoptionBoards(
            @RequestParam(required = false) UserType userType,
            @RequestParam(required = false) AnimalType animalType,
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String nickname
    ) {
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = animalAdoptionBoardService.searchAnimalAdoptionBoard(userType, animalType, species, nickname);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(animalAdoptionBoardResponseDtos);
        return responseModel;
    }

    @Operation(summary = "동물 분양 게시글 수정", description = "동물 분양 게시글 수정")
    @PatchMapping("/{animalAdoptionBoardId}")
    public ResponseModel updateAnimalAdoptionBoard(
            @PathVariable Long animalAdoptionBoardId,
            @RequestBody AnimalAdoptionBoardRequestDto requestDto
    ) throws PermissionDeniedException {
        animalAdoptionBoardService.updateAnimalAdoptionBoard(animalAdoptionBoardId, requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "동물 분양 게시글 삭제", description = "동물 분양 게시글 삭제(본인 또는 관리자만 가능)")
    @DeleteMapping("/{animalAdoptionBoardId}")
    public ResponseModel deleteAnimalAdoptionBoard(
            @PathVariable Long animalAdoptionBoardId
    ) throws PermissionDeniedException {
        animalAdoptionBoardService.deleteAnimalAdoptionBoard(animalAdoptionBoardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}

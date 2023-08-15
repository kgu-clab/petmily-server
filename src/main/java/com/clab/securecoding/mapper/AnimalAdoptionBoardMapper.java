package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.AnimalAdoptionBoardRequestDto;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardResponseDto;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalAdoptionBoardMapper {

    public AnimalAdoptionBoard mapDtoToEntity(AnimalAdoptionBoardRequestDto animalAdoptionBoardReqDto) {

        return AnimalAdoptionBoard.builder()
                .animalType(animalAdoptionBoardReqDto.getAnimalType())
                .species(animalAdoptionBoardReqDto.getSpecies())
                .name(animalAdoptionBoardReqDto.getName())
                .color(animalAdoptionBoardReqDto.getColor())
                .gender(animalAdoptionBoardReqDto.getGender())
                .age(animalAdoptionBoardReqDto.getAge())
                .weight(animalAdoptionBoardReqDto.getWeight())
                .vaccine(animalAdoptionBoardReqDto.getVaccine())
                .isNeutered(animalAdoptionBoardReqDto.getIsNeutered())
                .birthDay(animalAdoptionBoardReqDto.getBirthDay())
                .favoriteSnack(animalAdoptionBoardReqDto.getFavoriteSnack())
                .reasonForAdoption(animalAdoptionBoardReqDto.getReasonForAdoption())
                .areasAvailable(animalAdoptionBoardReqDto.getAreasAvailable())
                .price(animalAdoptionBoardReqDto.getPrice())
                .leadership(animalAdoptionBoardReqDto.getLeadership())
                .independence(animalAdoptionBoardReqDto.getIndependence())
                .initiative(animalAdoptionBoardReqDto.getInitiative())
                .positivity(animalAdoptionBoardReqDto.getPositivity())
                .adaptability(animalAdoptionBoardReqDto.getAdaptability())
                .recommendation(animalAdoptionBoardReqDto.getRecommendation())
                .think(animalAdoptionBoardReqDto.getThink())
                .build();
    }

    public AnimalAdoptionBoardResponseDto mapEntityToDto(AnimalAdoptionBoard animalAdoptionBoard) {

        return AnimalAdoptionBoardResponseDto.builder()
                .id(animalAdoptionBoard.getId())
                .animalType(animalAdoptionBoard.getAnimalType())
                .species(animalAdoptionBoard.getSpecies())
                .name(animalAdoptionBoard.getName())
                .color(animalAdoptionBoard.getColor())
                .gender(animalAdoptionBoard.getGender())
                .age(animalAdoptionBoard.getAge())
                .weight(animalAdoptionBoard.getWeight())
                .vaccine(animalAdoptionBoard.getVaccine())
                .isNeutered(animalAdoptionBoard.getIsNeutered())
                .birthDay(animalAdoptionBoard.getBirthDay())
                .favoriteSnack(animalAdoptionBoard.getFavoriteSnack())
                .reasonForAdoption(animalAdoptionBoard.getReasonForAdoption())
                .areasAvailable(animalAdoptionBoard.getAreasAvailable())
                .price(animalAdoptionBoard.getPrice())
                .leadership(animalAdoptionBoard.getLeadership())
                .independence(animalAdoptionBoard.getIndependence())
                .initiative(animalAdoptionBoard.getInitiative())
                .positivity(animalAdoptionBoard.getPositivity())
                .adaptability(animalAdoptionBoard.getAdaptability())
                .recommendation(animalAdoptionBoard.getRecommendation())
                .think(animalAdoptionBoard.getThink())
                .writer(animalAdoptionBoard.getWriter())
                .createdAt(animalAdoptionBoard.getCreatedAt())
                .build();
    }

}
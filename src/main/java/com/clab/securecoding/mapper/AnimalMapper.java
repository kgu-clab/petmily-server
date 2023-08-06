package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.AnimalRequestDto;
import com.clab.securecoding.type.dto.AnimalResponseDto;
import com.clab.securecoding.type.entity.Animal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalMapper {

    public Animal mapDtoToEntity(AnimalRequestDto animalRequestDto) {
        Animal animal = Animal.builder()
                .species(animalRequestDto.getSpecies())
                .age(animalRequestDto.getAge())
                .gender(animalRequestDto.getGender())
                .specialNotes(animalRequestDto.getSpecialNotes())
                .vaccine(animalRequestDto.getVaccine())
                .isNeutered(animalRequestDto.getIsNeutered())
                .reasonForAdoption(animalRequestDto.getReasonForAdoption())
                .previousHomeEnvironment(animalRequestDto.getPreviousHomeEnvironment())
                .likes(animalRequestDto.getLikes())
                .dislikes(animalRequestDto.getDislikes())
                .build();
        return animal;
    }

    public AnimalResponseDto mapEntityToDto(Animal animal) {
        AnimalResponseDto animalResponseDto = AnimalResponseDto.builder()
                .id(animal.getId())
                .species(animal.getSpecies())
                .age(animal.getAge())
                .gender(animal.getGender())
                .specialNotes(animal.getSpecialNotes())
                .vaccine(animal.getVaccine())
                .isNeutered(animal.getIsNeutered())
                .reasonForAdoption(animal.getReasonForAdoption())
                .previousHomeEnvironment(animal.getPreviousHomeEnvironment())
                .likes(animal.getLikes())
                .dislikes(animal.getDislikes())
                .user(animal.getUser())
                .build();
        return animalResponseDto;
    }

    public List<AnimalResponseDto> mapEntityToDto(List<Animal> animals) {
        List<AnimalResponseDto> animalResponseDtos = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalResponseDto animalResponseDto = mapEntityToDto(animal);
            animalResponseDtos.add(animalResponseDto);
        }
        return animalResponseDtos;
    }

}

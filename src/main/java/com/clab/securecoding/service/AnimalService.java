package com.clab.securecoding.service;

import com.clab.securecoding.auth.util.AuthUtil;
import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.repository.AnimalRepository;
import com.clab.securecoding.type.dto.AnimalRequestDto;
import com.clab.securecoding.type.dto.AnimalResponseDto;
import com.clab.securecoding.type.entity.Animal;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final UserService userService;

    public void createAnimal(AnimalRequestDto animalResquestDto) {
        Long userId = AuthUtil.getAuthenticationInfoUserId();
        User user = userService.getUserByIdOrThrow(userId);
        Animal animal = toAnimal(animalResquestDto);
        animal.setUser(user);
        animalRepository.save(animal);
    }

    public List<AnimalResponseDto> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        List<AnimalResponseDto> animalResponseDtos = toAnimalResponseDto(animals);
        return animalResponseDtos;
    }

    public List<AnimalResponseDto> searchAnimal(Long userId, String species) {
        List<Animal> animals = null;
        if (userId != null)
            animals = animalRepository.findAllByUser_Id(userId);
        else if (species != null)
            animals = animalRepository.findAllBySpecies(species);
        else
            throw new IllegalArgumentException("적어도 userId 또는 species 중 하나를 제공해야 합니다.");

        if (animals == null)
            throw new SearchResultNotExistException("검색 결과가 존재하지 않습니다.");
        List<AnimalResponseDto> animalResponseDtos = toAnimalResponseDto(animals);
        return animalResponseDtos;
    }

    public void updateAnimalInfoByUser(Long animalId, AnimalRequestDto animalRequestDto) {
        Animal animal = getAnimalByIdOrThrow(animalId);
        animal.setSpecies(animalRequestDto.getSpecies());
        animal.setAge(animalRequestDto.getAge());
        animal.setGender(animalRequestDto.getGender());
        animal.setSpecialNotes(animalRequestDto.getSpecialNotes());
        animal.setVaccine(animalRequestDto.getVaccine());
        animal.setIsNeutered(animalRequestDto.getIsNeutered());
        animal.setReasonForAdoption(animalRequestDto.getReasonForAdoption());
        animal.setPreviousHomeEnvironment(animalRequestDto.getPreviousHomeEnvironment());
        animal.setLikes(animalRequestDto.getLikes());
        animal.setDislikes(animalRequestDto.getDislikes());
        animalRepository.save(animal);
    }

    public Animal getAnimalByIdOrThrow(Long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException("해당 동물이 없습니다."));
    }

    private Animal toAnimal(AnimalRequestDto animalRequestDto) {
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

    private AnimalResponseDto toAnimalResponseDto(Animal animal) {
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

    private List<AnimalResponseDto> toAnimalResponseDto(List<Animal> animals) {
        List<AnimalResponseDto> animalResponseDtos = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalResponseDto animalResponseDto = toAnimalResponseDto(animal);
            animalResponseDtos.add(animalResponseDto);
        }
        return animalResponseDtos;
    }

}

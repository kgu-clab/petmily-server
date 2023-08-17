package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.AnimalMapper;
import com.clab.securecoding.repository.AnimalRepository;
import com.clab.securecoding.type.dto.AnimalRequestDto;
import com.clab.securecoding.type.dto.AnimalResponseDto;
import com.clab.securecoding.type.entity.Animal;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.AnimalType;
import com.clab.securecoding.type.etc.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final UserService userService;

    private final AnimalMapper animalMapper;

    @Transactional
    public void createAnimal(AnimalRequestDto animalResquestDto) {
        User user = userService.getCurrentUser();
        Animal animal = animalMapper.mapDtoToEntity(animalResquestDto);
        animal.setUser(user);
        animalRepository.save(animal);
    }

    public List<AnimalResponseDto> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        List<AnimalResponseDto> animalResponseDtos = animalMapper.mapEntityToDto(animals);
        return animalResponseDtos;
    }

    public List<AnimalResponseDto> searchAnimal(UserType userType, AnimalType animalType) {
        List<Animal> animals = null;
        if (userType != null && animalType != null) {
            animals = animalRepository.findAllByUser_TypeAndAnimalType(userType, animalType);
        }
        else if (userType != null) {
            animals = animalRepository.findAllByUser_Type(userType);
        }
        else if (animalType != null) {
            animals = animalRepository.findAllByAnimalType(animalType);
        }
        else {
            throw new IllegalArgumentException("적어도 userType, animalType 중 하나를 제공해야 합니다.");
        }

        if (animals == null) {
            throw new SearchResultNotExistException();
        }

        List<AnimalResponseDto> animalResponseDtos = animalMapper.mapEntityToDto(animals);
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

    public void deleteAnimal(Long animalId) {
        getAnimalByIdOrThrow(animalId);
        animalRepository.deleteById(animalId);
    }

    public Animal getAnimalByIdOrThrow(Long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException("해당 동물이 없습니다."));
    }

}

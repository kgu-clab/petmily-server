package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.AnimalAdoptionBoardMapper;
import com.clab.securecoding.repository.AnimalAdoptionBoardRepository;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardRequestDto;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardResponseDto;
import com.clab.securecoding.type.entity.*;
import com.clab.securecoding.type.etc.AnimalType;
import com.clab.securecoding.type.etc.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalAdoptionBoardService {

    private final AnimalAdoptionBoardRepository animalAdoptionBoardRepository;

    private final AnimalAdoptionBoardMapper animalAdoptionBoardMapper;

    private final UserService userService;

    public void createAnimalAdoptionBoard(AnimalAdoptionBoardRequestDto requestDto) {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardMapper.mapDtoToEntity(requestDto);
        animalAdoptionBoard.setWriter(writer);

        animalAdoptionBoardRepository.save(animalAdoptionBoard);
    }

    public List<AnimalAdoptionBoardResponseDto> getAnimalAdoptionBoards() {
        List<AnimalAdoptionBoard> animalAdoptionBoards = animalAdoptionBoardRepository.findAll();
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = new ArrayList<>();
        for (AnimalAdoptionBoard animalAdoptionBoard : animalAdoptionBoards) {
            AnimalAdoptionBoardResponseDto responseDto = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoard);
            animalAdoptionBoardResponseDtos.add(responseDto);
        }
        return animalAdoptionBoardResponseDtos;
    }

    public List<AnimalAdoptionBoardResponseDto> searchAnimalAdoptionBoard(
            UserType userType, AnimalType animalType, String species, String gender, Long age, String nickname
    ) {
        List<AnimalAdoptionBoard> animalAdoptionBoards = new ArrayList<>();
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = new ArrayList<>();

        if (userType != null) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByWriter_Type(userType);
        } else if (animalType != null) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByAnimalType(animalType);
        } else if (species != null) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findBySpecies(species);
        } else if (gender != null) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByGender(gender);
        } else if ( age != null ) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByAge(age);
        } else if ( nickname != null ) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByWriter_Nickname(nickname);
        } else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (animalAdoptionBoards.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        for (AnimalAdoptionBoard animalAdoptionBoard : animalAdoptionBoards) {
            AnimalAdoptionBoardResponseDto responseDto = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoard);
            animalAdoptionBoardResponseDtos.add(responseDto);
        }
        return animalAdoptionBoardResponseDtos;
    }

    public void updateAnimalAdoptionBoard(Long animalAdoptionBoardId, AnimalAdoptionBoardRequestDto requestDto) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = getAnimalAdoptionBoardByIdOrThrow(animalAdoptionBoardId);

        if (writer != animalAdoptionBoard.getWriter()) {
            throw new PermissionDeniedException();
        }

        setAnimalAdoptionBoard(animalAdoptionBoard, requestDto);
        animalAdoptionBoardRepository.save(animalAdoptionBoard);
    }

    public void deleteAnimalAdoptionBoard(Long animalAdoptionBoardId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = getAnimalAdoptionBoardByIdOrThrow(animalAdoptionBoardId);
        if (writer == animalAdoptionBoard.getWriter() || userService.checkUserAdminRole()) {
            animalAdoptionBoardRepository.delete(animalAdoptionBoard);
        } else {
            throw new PermissionDeniedException();
        }
    }

    public AnimalAdoptionBoard getAnimalAdoptionBoardByIdOrThrow(Long animalAdoptionBoardId) {
        return animalAdoptionBoardRepository.findById(animalAdoptionBoardId)
                .orElseThrow(() -> new NotFoundException("해당하는 동물 분양 게시글이 없습니다."));
    }

    public void setAnimalAdoptionBoard(AnimalAdoptionBoard animalAdoptionBoard, AnimalAdoptionBoardRequestDto requestDto) {
        animalAdoptionBoard.setAnimalType(requestDto.getAnimalType());
        animalAdoptionBoard.setSpecies(requestDto.getSpecies());
        animalAdoptionBoard.setName(requestDto.getName());
        animalAdoptionBoard.setColor(requestDto.getColor());
        animalAdoptionBoard.setGender(requestDto.getGender());
        animalAdoptionBoard.setAge(requestDto.getAge());
        animalAdoptionBoard.setWeight(requestDto.getWeight());
        animalAdoptionBoard.setVaccine(requestDto.getVaccine());
        animalAdoptionBoard.setIsNeutered(requestDto.getIsNeutered());
        animalAdoptionBoard.setReasonForAdoption(requestDto.getReasonForAdoption());
        animalAdoptionBoard.setPrice(requestDto.getPrice());
        animalAdoptionBoard.setLeadership(requestDto.getLeadership());
        animalAdoptionBoard.setIndependence(requestDto.getIndependence());
        animalAdoptionBoard.setInitiative(requestDto.getInitiative());
        animalAdoptionBoard.setPositivity(requestDto.getPositivity());
        animalAdoptionBoard.setAdaptability(requestDto.getAdaptability());
        animalAdoptionBoard.setAreasAvailable(requestDto.getAreasAvailable());
        animalAdoptionBoard.setRecommendation(requestDto.getRecommendation());
        animalAdoptionBoard.setThink(requestDto.getThink());
    }

}
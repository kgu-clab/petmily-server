package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.AnimalAdoptionBoardMapper;
import com.clab.securecoding.repository.AdoptionRequestRepository;
import com.clab.securecoding.repository.AnimalAdoptionBoardRepository;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardRequestDto;
import com.clab.securecoding.type.dto.AnimalAdoptionBoardResponseDto;
import com.clab.securecoding.type.entity.AdoptionRequest;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
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

    private final AdoptionRequestRepository adoptionRequestRepository;

    public void createAnimalAdoptionBoard(AnimalAdoptionBoardRequestDto requestDto) {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardMapper.mapDtoToEntity(requestDto);
        animalAdoptionBoard.setWriter(writer);

        animalAdoptionBoardRepository.save(animalAdoptionBoard);
    }

    public List<AnimalAdoptionBoardResponseDto> getAnimalAdoptionBoards() {
        List<AnimalAdoptionBoard> animalAdoptionBoards = animalAdoptionBoardRepository.findAll();
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoards);
        return animalAdoptionBoardResponseDtos;
    }

    public List<AnimalAdoptionBoardResponseDto> getMyAnimalAdoptionBoard() {
        User user = userService.getCurrentUser();
        List<AnimalAdoptionBoard> animalAdoptionBoards = animalAdoptionBoardRepository.findByWriter(user);
        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoards);
        return animalAdoptionBoardResponseDtos;
    }

    public AnimalAdoptionBoardResponseDto getAnimalAdoptionBoard(Long boardId) {
        AnimalAdoptionBoard animalAdoptionBoard = getAnimalAdoptionBoardByIdOrThrow(boardId);
        AnimalAdoptionBoardResponseDto animalAdoptionBoardResponseDto = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoard);
        return animalAdoptionBoardResponseDto;
    }

    public List<AnimalAdoptionBoardResponseDto> searchAnimalAdoptionBoard(
            UserType userType, AnimalType animalType, String species, String nickname
    ) {
        List<AnimalAdoptionBoard> animalAdoptionBoards = new ArrayList<>();

        if (userType != null) {
            animalAdoptionBoards = animalAdoptionBoardRepository.findByWriter_Type(userType);
        }

        if (animalType != null) {
            if (!animalAdoptionBoards.isEmpty()) {
                animalAdoptionBoards.retainAll(animalAdoptionBoardRepository.findByAnimalType(animalType));
            } else {
                animalAdoptionBoards = animalAdoptionBoardRepository.findByAnimalType(animalType);
            }
        }

        if (species != null) {
            if (!animalAdoptionBoards.isEmpty()) {
                animalAdoptionBoards.retainAll(animalAdoptionBoardRepository.findBySpecies(species));
            } else {
                animalAdoptionBoards = animalAdoptionBoardRepository.findBySpecies(species);
            }
        }

        if (nickname != null) {
            if (!animalAdoptionBoards.isEmpty()) {
                animalAdoptionBoards.retainAll(animalAdoptionBoardRepository.findByWriter_Nickname(nickname));
            } else {
                animalAdoptionBoards = animalAdoptionBoardRepository.findByWriter_Nickname(nickname);
            }
        }

        if (userType == null && animalType == null && species == null && nickname == null) {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (animalAdoptionBoards.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        List<AnimalAdoptionBoardResponseDto> animalAdoptionBoardResponseDtos = animalAdoptionBoardMapper.mapEntityToDto(animalAdoptionBoards);
        return animalAdoptionBoardResponseDtos;
    }

    public void updateAnimalAdoptionBoard(Long animalAdoptionBoardId, AnimalAdoptionBoardRequestDto requestDto) throws PermissionDeniedException {
        AnimalAdoptionBoard animalAdoptionBoard = getAnimalAdoptionBoardByIdOrThrow(animalAdoptionBoardId);

        checkWriterPermission(animalAdoptionBoard.getWriter());

        animalAdoptionBoard = animalAdoptionBoardMapper.mapDtoToEntity(requestDto);
        animalAdoptionBoardRepository.save(animalAdoptionBoard);
    }

    public void deleteAnimalAdoptionBoard(Long animalAdoptionBoardId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = getAnimalAdoptionBoardByIdOrThrow(animalAdoptionBoardId);
        if (checkWriterPermission(animalAdoptionBoard.getWriter()) || userService.checkUserAdminRole()) {
            animalAdoptionBoardRepository.delete(animalAdoptionBoard);
        } else {
            throw new PermissionDeniedException();
        }
    }

    public AnimalAdoptionBoard getAnimalAdoptionBoardByIdOrThrow(Long animalAdoptionBoardId) {
        return animalAdoptionBoardRepository.findById(animalAdoptionBoardId)
                .orElseThrow(() -> new NotFoundException("해당하는 동물 분양 게시글이 없습니다."));
    }

    public List<User> getAdoptionRequestUser(Long boardId) {
        List<AdoptionRequest> adoptionRequests = adoptionRequestRepository.findAllByAnimalAdoptionBoard_Id(boardId);
        List<User> requestUser = new ArrayList<>();
        for (AdoptionRequest adoptionRequest : adoptionRequests) {
            requestUser.add(adoptionRequest.getUser());
        }
        return requestUser;
    }

    private boolean checkWriterPermission(User boardWriter) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        if (!writer.equals(boardWriter)) {
            throw new PermissionDeniedException();
        }
        return true;
    }

}

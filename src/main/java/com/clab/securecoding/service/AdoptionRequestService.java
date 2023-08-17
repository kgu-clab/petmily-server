package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.type.dto.AdoptionReserveRequestDto;
import com.clab.securecoding.type.entity.AdoptionRequest;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.RequestState;
import com.clab.securecoding.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;

    private final UserService userService;

    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    public void sendAdoptionRequest(AdoptionReserveRequestDto requestDto) {
        User user = userService.getCurrentUser();
        Long boardId = requestDto.getAnimalAdoptionBoardId();
        AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardService.getAnimalAdoptionBoardByIdOrThrow(boardId);
        if (!isExistAdoptionRequest(user, animalAdoptionBoard)) {
            AdoptionRequest adoptionRequest = createAdoptionRequest(user, animalAdoptionBoard);
            adoptionRequestRepository.save(adoptionRequest);
        }
    }

    public List<AdoptionRequest> getAdoptionRequest() {
        User user = userService.getCurrentUser();
        List<AdoptionRequest> adoptionRequest = getAdoptionRequestByAnimalAdoptionBoard_Writer(user);
        return adoptionRequest;
    }

    public void approveAdoptionRequest(Long requestId) {
        List<AdoptionRequest> adoptionRequests = adoptionRequestRepository.findAll();
        for (AdoptionRequest adoptionRequest : adoptionRequests) {
            adoptionRequest.setRequestState(RequestState.CANCEL);
            adoptionRequestRepository.save(adoptionRequest);
        }
        AdoptionRequest adoptionRequest = getAdoptionRequestByRequestIdOrThrow(requestId);
        adoptionRequest.setRequestState(RequestState.APPROVE);
        adoptionRequestRepository.save(adoptionRequest);
    }

    public void deleteAdoptionRequest(Long requestId) {
        AdoptionRequest adoptionRequest = getAdoptionRequestByRequestIdOrThrow(requestId);
        adoptionRequestRepository.delete(adoptionRequest);
    }

    public boolean isExistAdoptionRequest(User user, AnimalAdoptionBoard animalAdoptionBoard) {
        return adoptionRequestRepository.existsByUserAndAnimalAdoptionBoard(user, animalAdoptionBoard);
    }

    public AdoptionRequest getAdoptionRequestByRequestIdOrThrow(Long requestId) {
        return adoptionRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("해당 분양 요청이 없습니다."));
    }

    public List<AdoptionRequest> getAdoptionRequestByAnimalAdoptionBoard_Writer(User user) {
        return adoptionRequestRepository.findAllByAnimalAdoptionBoard_Writer(user);
    }

    private AdoptionRequest createAdoptionRequest(User user, AnimalAdoptionBoard animalAdoptionBoard) {
        AdoptionRequest adoptionRequest = AdoptionRequest.builder()
                .user(user)
                .animalAdoptionBoard(animalAdoptionBoard)
                .requestState(RequestState.WAIT)
                .build();
        return adoptionRequest;
    }

}

package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.repository.AnimalAdoptionBoardRepository;
import com.clab.securecoding.type.dto.AdoptionReserveRequestDto;
import com.clab.securecoding.type.dto.RequestDto;
import com.clab.securecoding.type.entity.AdoptionRequest;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.AnimalState;
import com.clab.securecoding.type.etc.RequestState;
import com.clab.securecoding.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionRequestService {

    private final UserService userService;

    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    private final SmsService smsService;

    private final AdoptionRequestRepository adoptionRequestRepository;

    private final AnimalAdoptionBoardRepository animalAdoptionBoardRepository;

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

    public List<AdoptionRequest> getMyAdoptionRequest() {
        User user = userService.getCurrentUser();
        List<AdoptionRequest> adoptionRequest = getAdoptionRequestByUser(user);
        return adoptionRequest;
    }

    @Transactional
    public void approveAdoptionRequest(Long requestId) {
        List<AdoptionRequest> adoptionRequests = adoptionRequestRepository.findAll();
        for (AdoptionRequest adoptionRequest : adoptionRequests) {
            adoptionRequest.setRequestState(RequestState.CANCEL);
            adoptionRequestRepository.save(adoptionRequest);
        }
        AdoptionRequest adoptionRequest = getAdoptionRequestByRequestIdOrThrow(requestId);
        adoptionRequest.setRequestState(RequestState.APPROVE);
        AnimalAdoptionBoard animalAdoptionBoard = adoptionRequest.getAnimalAdoptionBoard();
        animalAdoptionBoard.setAnimalState(AnimalState.COMPLETE);
        animalAdoptionBoardRepository.save(animalAdoptionBoard);
        adoptionRequestRepository.save(adoptionRequest);
        RequestDto requestDto = RequestDto.builder()
                .recipientPhoneNumber(adoptionRequest.getUser().getContact())
                .content("분양 요청이 승인되었습니다.\n홈페이지에서 확인 부탁드립니다.")
                .build();
        smsService.sendSms(requestDto);
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

    public List<AdoptionRequest> getAdoptionRequestByUser(User user) {
        return adoptionRequestRepository.findAllByUser(user);
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

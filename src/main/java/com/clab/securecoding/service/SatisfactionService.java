package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.SatisfactionMapper;
import com.clab.securecoding.repository.SatisfactionRepository;
import com.clab.securecoding.type.dto.SatisfactionRequestDto;
import com.clab.securecoding.type.dto.SatisfactionResponseDto;
import com.clab.securecoding.type.entity.Satisfaction;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SatisfactionService {

    private final SatisfactionMapper satisfactionMapper;

    private final SatisfactionRepository satisfactionRepository;

    private final UserService userService;

    public void createSatisfaction(SatisfactionRequestDto requestDto) {
        Satisfaction satisfaction = satisfactionMapper.mapDtoToEntity(requestDto);
        satisfaction.setWriter(userService.getCurrentUser());
        satisfactionRepository.save(satisfaction);
    }

    public List<SatisfactionResponseDto> getSatisfactions() {
        List<Satisfaction> satisfactions = satisfactionRepository.findAll();
        List<SatisfactionResponseDto> satisfactionResponseDtos = new ArrayList<>();
        for (Satisfaction satisfaction : satisfactions) {
            SatisfactionResponseDto satisfactionResponseDto = satisfactionMapper.mapEntityToDto(satisfaction);
            satisfactionResponseDtos.add(satisfactionResponseDto);
        }
        return satisfactionResponseDtos;
    }

    public List<SatisfactionResponseDto> searchSatisfactions(Long userId) {
        List<Satisfaction> satisfactions = new ArrayList<>();
        List<SatisfactionResponseDto> satisfactionResponseDtos = new ArrayList<>();

        if (userId != null) {
            User user = userService.getUserByIdOrThrow(userId);
            satisfactions = satisfactionRepository.findByWriter(user);
        } else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (satisfactions.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        for (Satisfaction satisfaction : satisfactions) {
            SatisfactionResponseDto satisfactionResponseDto = satisfactionMapper.mapEntityToDto(satisfaction);
            satisfactionResponseDtos.add(satisfactionResponseDto);
        }
        return satisfactionResponseDtos;
    }

    public void deleteSatisfaction(Long satisfactionId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        Satisfaction satisfaction = getSatisfactionByIdOrThrow(satisfactionId);

        if (writer == satisfaction.getWriter() || userService.checkUserAdminRole()) {
            satisfactionRepository.delete(satisfaction);
        } else {
            throw new PermissionDeniedException();
        }
    }

    public Satisfaction getSatisfactionByIdOrThrow(Long satisfactionId) {
        return satisfactionRepository.findById(satisfactionId)
                .orElseThrow(() -> new NotFoundException("해당 만족도 조사가 없습니다"));
    }

}

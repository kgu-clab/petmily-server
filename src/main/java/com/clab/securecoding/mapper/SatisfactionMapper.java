package com.clab.securecoding.mapper;

import com.clab.securecoding.service.AnimalAdoptionBoardService;
import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.SatisfactionRequestDto;
import com.clab.securecoding.type.dto.SatisfactionResponseDto;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.Satisfaction;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SatisfactionMapper {

    private final UserService userService;
    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    public Satisfaction mapDtoToEntity(SatisfactionRequestDto requestDto) {
        User writer = userService.getCurrentUser();
        AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardService.getAnimalAdoptionBoardByIdOrThrow(requestDto.getAnimalAdoptionBoardId());

        return Satisfaction.builder()
                .writer(writer)
                .animalAdoptionBoard(animalAdoptionBoard)
                .adoptionProcessSatisfaction(requestDto.getAdoptionProcessSatisfaction())
                .mostSatisfyingThing(requestDto.getMostSatisfyingThing())
                .guideContentSatisfaction(requestDto.getGuideContentSatisfaction())
                .secondhandTransactionSatisfaction(requestDto.getSecondhandTransactionSatisfaction())
                .intentionToUse(requestDto.getIntentionToUse())
                .intentionToRecommendation(requestDto.getIntentionToRecommendation())
                .suggestionsForImprovement(requestDto.getSuggestionsForImprovement())
                .build();
    }

    public SatisfactionResponseDto mapEntityToDto(Satisfaction satisfaction) {
        return SatisfactionResponseDto.builder()
                .id(satisfaction.getId())
                .writer(satisfaction.getWriter())
                .animalAdoptionBoard(satisfaction.getAnimalAdoptionBoard())
                .adoptionProcessSatisfaction(satisfaction.getAdoptionProcessSatisfaction())
                .mostSatisfyingThing(satisfaction.getMostSatisfyingThing())
                .guideContentSatisfaction(satisfaction.getGuideContentSatisfaction())
                .secondhandTransactionSatisfaction(satisfaction.getSecondhandTransactionSatisfaction())
                .intentionToUse(satisfaction.getIntentionToUse())
                .intentionToRecommendation(satisfaction.getIntentionToRecommendation())
                .suggestionsForImprovement(satisfaction.getSuggestionsForImprovement())
                .build();
    }

}

package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SatisfactionResponseDto {

    private Long id;

    private User writer;

    private AnimalAdoptionBoard animalAdoptionBoard;

    private String adoptionProcessSatisfaction;

    private String mostSatisfyingThing;

    private String guideContentSatisfaction;

    private String secondhandTransactionSatisfaction;

    private String intentionToUse;

    private String intentionToRecommendation;

    private String suggestionsForImprovement;
}

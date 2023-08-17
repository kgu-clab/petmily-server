package com.clab.securecoding.type.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SatisfactionRequestDto {

    private Long animalAdoptionBoardId;

    private String adoptionProcessSatisfaction;

    private String mostSatisfyingThing;

    private String guideContentSatisfaction;

    private String secondhandTransactionSatisfaction;

    private String intentionToUse;

    private String intentionToRecommendation;

    private String suggestionsForImprovement;
}

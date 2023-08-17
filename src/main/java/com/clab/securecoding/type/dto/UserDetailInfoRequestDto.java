package com.clab.securecoding.type.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailInfoRequestDto {

    private String timeWithPets;

    private String environment;

    private String numberOfFamily;

    private Boolean hasAllergy;

    private String hospitalExpensesForPets;

    private String numberOfWalks;

    private Boolean hasAlreadyPet;

    private Boolean hasExperienceRaisingPets;

    private String sizeOfHouse;

    private String job;

    private String age;
}

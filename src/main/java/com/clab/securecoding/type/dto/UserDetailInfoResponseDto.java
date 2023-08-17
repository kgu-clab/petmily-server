package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDetailInfoResponseDto {

    private Long id;

    private User user;

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

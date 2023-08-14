package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnimalResponseDto {

    private Long id;

    private AnimalType animalType;

    private String species;

    private Long age;

    private String gender;

    private String specialNotes;

    private String vaccine;

    private Boolean isNeutered;

    private String reasonForAdoption;

    private String previousHomeEnvironment;

    private String likes;

    private String dislikes;

    private User user;

}

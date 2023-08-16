package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnimalAdoptionBoardRequestDto {

    private AnimalType animalType;

    private String species;

    private String name;

    private String color;

    private String gender;

    private Long age;

    private Double weight;

    private String vaccine;

    private Boolean isNeutered;

    private String birthDay;

    private String favoriteSnack;

    private String reasonForAdoption;

    private String areasAvailable;

    private Integer price;

    private Double leadership;

    private Double independence;

    private Double initiative;

    private Double positivity;

    private Double adaptability;

    private String recommendation;

    private String think;

}

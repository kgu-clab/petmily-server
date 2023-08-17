package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.Contract;
import com.clab.securecoding.type.etc.RequestState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdoptionRequestResponseDto {

    private AnimalAdoptionBoard animalAdoptionBoard;

    private RequestState requestState;

    private List<Contract> contracts;

}

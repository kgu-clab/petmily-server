package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.AdoptionRequestResponseDto;
import com.clab.securecoding.type.entity.AdoptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdoptionRequestMapper {

    public AdoptionRequestResponseDto mapDtoToEntity(AdoptionRequest adoptionRequest) {
        return AdoptionRequestResponseDto.builder()
                .animalAdoptionBoard(adoptionRequest.getAnimalAdoptionBoard())
                .requestState(adoptionRequest.getRequestState())
                .build();
    }

    public List<AdoptionRequestResponseDto> mapDtoToEntity(List<AdoptionRequest> adoptionRequest) {
        List<AdoptionRequestResponseDto> adoptionRequestResponseDtos = new ArrayList<>();
        for (AdoptionRequest request : adoptionRequest) {
            adoptionRequestResponseDtos.add(mapDtoToEntity(request));
        }
        return adoptionRequestResponseDtos;
    }

}

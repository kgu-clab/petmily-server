package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.ContractRequestDto;
import com.clab.securecoding.type.entity.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContractMapper {

    private final AnimalMapper animalMapper;

    public Contract mapDtoToEntity(ContractRequestDto contractRequestDto) {
        Contract contract = Contract.builder()
                .content(contractRequestDto.getContent())
                .animal(animalMapper.mapDtoToEntity(contractRequestDto.getAnimal()))
                .additionalProvisions(contractRequestDto.getAdditionalProvisions())
                .signature(contractRequestDto.getSignature())
                .build();
        return contract;
    }

}

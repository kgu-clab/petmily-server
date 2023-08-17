package com.clab.securecoding.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractRequestDto {

    private String content;

    private String gender;

    private Long age;

    private String vaccine;

    private String isNeutered;

    private String additionalProvisions;

    private String signature;

    private Long AdoptionRequestId;

}

package com.clab.securecoding.type.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractRequestDto {

    private String content;

    private AnimalRequestDto animal;

    private String additionalProvisions;

    private String signature;

}

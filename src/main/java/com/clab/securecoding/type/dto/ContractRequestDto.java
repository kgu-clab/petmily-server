package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.Animal;
import com.clab.securecoding.type.entity.User;
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

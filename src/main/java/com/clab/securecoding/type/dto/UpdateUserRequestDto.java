package com.clab.securecoding.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {

    private String password;

    private String nickname;

    private String email;

    private String address;

    private String contact;

    private String type;

}

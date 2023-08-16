package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String id;

    private String password;

    private String nickname;

    private String email;

    private String address;

    private String contact;

    private String businessNumber;

    private UserType type;

}

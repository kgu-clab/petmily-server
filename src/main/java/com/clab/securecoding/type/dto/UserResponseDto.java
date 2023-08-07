package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.OAuthProvider;
import com.clab.securecoding.type.etc.Role;
import com.clab.securecoding.type.etc.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long seq;

    private String id;

    private String nickname;

    private String email;

    private String address;

    private String contact;

    private UserType type;

    private Role role;

    private OAuthProvider provider;

    private LocalDateTime createdAt;

}

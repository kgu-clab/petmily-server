package com.clab.securecoding.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    PROFESSIONAL("전문업체"),
    SHELTER("보호소"),
    INDIVIDUAL("개인무료");

    private final String value;

}

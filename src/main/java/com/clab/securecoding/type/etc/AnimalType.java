package com.clab.securecoding.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalType {

    DOG("강아지"),
    CAT("고양이"),
    BIRD("새"),
    FISH("물고기"),
    SMALL_ANIMAL("소형 동물"),
    REPTILE("파충류");

    private final String value;

}
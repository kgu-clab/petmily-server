package com.clab.securecoding.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalState {

    FIND("찾는 중"),
    COMPLETE("완료");

    private final String value;

}

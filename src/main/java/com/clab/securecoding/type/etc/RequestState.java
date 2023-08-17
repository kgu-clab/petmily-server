package com.clab.securecoding.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestState {

    CANCEL("취소"),
    APPROVE("승인"),
    WAIT("대기");

    private final String value;

}

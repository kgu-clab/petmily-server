package com.clab.securecoding.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OAuthProvider {

    LOCAL("일반"),
    GOOGLE("구글"),
    NAVER("네이버"),
    KAKAO("카카오");

    private final String value;

}

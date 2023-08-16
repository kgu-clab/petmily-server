package com.clab.securecoding.exception;

public class UserLockedException extends Exception {

    private static final String DEFAULT_MESSAGE = "연속된 로그인 실패로 인해 일시적으로 계정이 잠겼습니다.";

    public UserLockedException() {
        super(DEFAULT_MESSAGE);
    }

    public UserLockedException(String s) {
        super(s);
    }

}
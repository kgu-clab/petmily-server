package com.clab.securecoding.exception;

public class DuplicateContactException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "이미 존재하는 연락처입니다.";

    public DuplicateContactException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateContactException(String s) {
        super(s);
    }

}

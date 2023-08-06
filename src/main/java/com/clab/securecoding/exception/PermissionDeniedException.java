package com.clab.securecoding.exception;

public class PermissionDeniedException extends Exception {

    private static final String DEFAULT_MESSAGE = "권한이 없습니다.";

    public PermissionDeniedException() {
        super(DEFAULT_MESSAGE);
    }

    public PermissionDeniedException(String s) {
        super(s);
    }

}
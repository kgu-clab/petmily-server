package com.clab.securecoding.exception;

public class ApiRequestFailedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "API 요청에 실패했습니다.";

    public ApiRequestFailedException() {
        super(DEFAULT_MESSAGE);
    }

    public ApiRequestFailedException(String s) {
        super(s);
    }

}

package com.clab.securecoding.exception;

public class VerificationFailedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "유효하지 않은 인증입니다.";

    public VerificationFailedException() {
        super(DEFAULT_MESSAGE);
    }

    public VerificationFailedException(String s) {
        super(s);
    }

}

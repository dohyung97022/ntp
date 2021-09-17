package com.nt.ntp.exception;

public class NoAuthenticationCookieException extends RuntimeException {

    public NoAuthenticationCookieException() {
        super("Authentication 쿠키가 존재하지 않습니다.");
    }
}

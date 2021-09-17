package com.nt.ntp.exception.controller;

import com.nt.ntp.exception.EmptyRequestParamException;
import com.nt.ntp.exception.InvalidGreenGoodsOfFactoryException;
import com.nt.ntp.exception.InvalidResponseException;
import com.nt.ntp.exception.NoAuthenticationCookieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.UnknownHostException;

@RestControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String conflict(MethodArgumentTypeMismatchException e) {
        String message = "parameter " + e.getName() + "가 없습니다";
        if (e.getName().equals("type"))
            message = "type parameter 가 없습니다. 분류 버튼을 눌러주세요.";
        return message;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoAuthenticationCookieException.class)
    public String noAuthenticationCookie(NoAuthenticationCookieException e) {
        return e.getMessage() + " 분류 버튼을 눌러 토큰을 받아주세요.";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnknownHostException.class)
    public String hostNotFound(UnknownHostException e) {
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyRequestParamException.class)
    public String emptyParameter(EmptyRequestParamException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidResponseException.class)
    public ResponseEntity<String> invalidResponse(InvalidResponseException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidGreenGoodsOfFactoryException.class)
    public String invalidGreenGoodsOfFactory(InvalidGreenGoodsOfFactoryException e) {
        return e.getMessage();
    }
}
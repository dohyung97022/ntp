package com.nt.ntp.exception;

import lombok.Getter;

@Getter
public class EmptyRequestParamException extends RuntimeException {
    private final String parameterName;

    public EmptyRequestParamException(String parameterName) {
        super("parameter " +  parameterName + " 가 존재하지 않습니다.");
        this.parameterName = parameterName;
    }
}

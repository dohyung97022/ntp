package com.nt.ntp.exception;

import com.nt.ntp.util.http.Response;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class InvalidResponseException extends RuntimeException {
    private final int code;
    private final Map<String, List<String>> header;
    private final byte[] body;

    public InvalidResponseException(Response response) {
        super("Response 의 결과가 " + response.getCode() + " 에 메시지는 " + new String(response.getBody()) + " 입니다.");
        this.code = response.getCode();
        this.header = response.getHeader();
        this.body = response.getBody();
    }
}

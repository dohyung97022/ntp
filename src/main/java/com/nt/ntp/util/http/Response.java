package com.nt.ntp.util.http;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Response {
    private final int code;
    private final String message;
    private final List<Header> headers = new ArrayList<>();
    private final JsonNode body;

    public Response(int code, String message, Map<String, List<String>> headers, JsonNode body) {
        this.code = code;
        this.message = message;
        setHeaders(headers);
        this.body = body;
    }

    private void setHeaders(Map<String, List<String>> map){
        map.forEach((key, values) -> this.headers.add(new Header(key, values)));
    }
}
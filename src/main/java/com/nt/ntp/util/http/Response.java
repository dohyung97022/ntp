package com.nt.ntp.util.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

@Getter
public class Response {
    private final int code;
    private final String message;
    private final Map<String, List<String>> header;
    private final byte[] body;

    public Response(int code, String message, Map<String, List<String>> header, byte[] body) {
        this.code = code;
        this.message = message;
        this.header = header;
        this.body = body;
    }

    public JsonNode getJsonNodeBody() throws IOException {
        return new ObjectMapper().readTree(this.body);
    }
}
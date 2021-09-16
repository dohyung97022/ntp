package com.nt.ntp.util.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Request {
    private final URI uri;
    private final RequestMethod requestMethod;
    private final Map<String, List<String>> header;

    public Request(URI uri) {
        this.uri = uri;
        this.requestMethod = RequestMethod.GET;
        this.header = null;
    }
}

package com.nt.ntp.util.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;

@Getter
@AllArgsConstructor
public class Request {
    private final URI uri;
    private final RequestMethod requestMethod;
    private final List<Header> headers;
}

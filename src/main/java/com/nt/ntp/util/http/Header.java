package com.nt.ntp.util.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Header {
    private final String key;
    private final List<String> values;

    public String getValues() {
        return String.join(";", values);
    }
}
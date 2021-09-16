package com.nt.ntp.util.http;

import lombok.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpUtils {
    public static Response connect(@NonNull Request request) throws IOException {
        // Request
        HttpURLConnection urlConnection = (HttpURLConnection) request.getUri().toURL().openConnection();
        urlConnection.setRequestMethod(request.getRequestMethod().toString());
        if (request.getHeader() != null)
            request.getHeader().forEach((key, values) -> urlConnection.setRequestProperty(key, joinHeaderValues(values)));

        // Response
        return new Response(
                urlConnection.getResponseCode(),
                urlConnection.getResponseMessage(),
                urlConnection.getHeaderFields(),
                urlConnection.getInputStream().readAllBytes()
        );
    }

    public static String joinHeaderValues(List<String> headerValues){
        return String.join(";", headerValues);
    }
}

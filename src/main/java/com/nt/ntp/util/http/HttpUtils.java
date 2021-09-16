package com.nt.ntp.util.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpUtils {
    public static Response connect(@NonNull Request request) throws IOException {
        // Request
        HttpURLConnection urlConnection = (HttpURLConnection) request.getUri().toURL().openConnection();
        urlConnection.setRequestMethod(request.getRequestMethod().toString());
        if ( request.getHeaders() != null)
            request.getHeaders().forEach(header -> urlConnection.setRequestProperty(header.getKey(), header.getValues()));

        // Response
        JsonNode body = new ObjectMapper().readTree(urlConnection.getInputStream());
        return new Response(
                urlConnection.getResponseCode(),
                urlConnection.getResponseMessage(),
                urlConnection.getHeaderFields(),
                body
        );
    }
}

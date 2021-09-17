package com.nt.ntp.util.http;

import lombok.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String getAccessTokenFromSetCookieValue(String setCookieValue) {
        Pattern pattern = Pattern.compile("Authorization=([^;^ ]+)");
        Matcher matcher = pattern.matcher(setCookieValue);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("setCookie 가 패턴에 맞지 않습니다.");
        }
    }
}

package com.nt.ntp.util.http;

import com.nt.ntp.exception.InvalidResponseException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {
    private static final List<Integer> errorCodes = List.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.NOT_FOUND.value());

    public static Response connect(@NonNull Request request) throws IOException {
        // Request
        HttpURLConnection urlConnection = (HttpURLConnection) request.getUri().toURL().openConnection();
        urlConnection.setRequestMethod(request.getRequestMethod().toString());
        if (request.getHeader() != null)
            request.getHeader().forEach((key, values) -> urlConnection.setRequestProperty(key, joinHeaderValues(values)));

        // Response
        Response response = new Response(
                urlConnection.getResponseCode(),
                urlConnection.getResponseMessage(),
                urlConnection.getHeaderFields(),
                (errorCodes.contains(urlConnection.getResponseCode()))
                        ? urlConnection.getErrorStream().readAllBytes() : urlConnection.getInputStream().readAllBytes());

        // Check
        if (HttpUtils.errorCodes.contains(urlConnection.getResponseCode()))
            throw new InvalidResponseException(response);

        return response;
    }

    public static String joinHeaderValues(List<String> headerValues) {
        return String.join(";", headerValues);
    }

    public static String getAccessTokenFromSetCookieValue(String setCookieValue) {
        String pattern = "Authorization=([^;^ ]+)";
        Matcher matcher = Pattern.compile("Authorization=([^;^ ]+)").matcher(setCookieValue);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new InvalidParameterException("setCookie " + setCookieValue + " 가 패턴 " + pattern + " 에 맞지 않습니다.");
        }
    }
}

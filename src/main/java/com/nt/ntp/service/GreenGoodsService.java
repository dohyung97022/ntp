package com.nt.ntp.service;

import com.nt.ntp.config.EndpointConfig;
import com.nt.ntp.util.enums.GreenGoods;
import com.nt.ntp.util.http.HttpUtils;
import com.nt.ntp.util.http.Request;
import com.nt.ntp.util.http.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class GreenGoodsService {
    private final EndpointConfig endpointConfig;

    public void setCookieToAccessToken(GreenGoods greenGoods, HttpServletResponse httpServletResponse) throws IOException {
        final String PATH = "token";
        final String ACCESS_TOKEN_KEY = "accessToken";

        final int COOKIE_DUE_DAY = 365 * 10;

        Response response;
        String token;
        URI uri;

        switch (greenGoods) {
            case FRUIT:
                uri = UriComponentsBuilder.fromUriString(endpointConfig.FRUIT_STORE_URL).path(PATH).build().toUri();
                response = HttpUtils.connect(new Request(uri));
                token = response.getJsonNodeBody().get(ACCESS_TOKEN_KEY).textValue();
                break;
            case VEGETABLE:
                uri = UriComponentsBuilder.fromUriString(endpointConfig.VEGETABLE_STORE_URL).path(PATH).build().toUri();
                response = HttpUtils.connect(new Request(uri));
                String setCookieValue = HttpUtils.joinHeaderValues(response.getHeader().get(HttpHeaders.SET_COOKIE));
                token = getAccessTokenFrom(setCookieValue);
                break;
            default:
                throw new IllegalArgumentException("GreenGoods 에 url 이 할당되지 않았습니다.");
        }

        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, token);
        cookie.setPath("/");
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(COOKIE_DUE_DAY));
        httpServletResponse.addCookie(cookie);
    }

    public List<String> getGoods(GreenGoods greenGoods, String authorization) throws IOException {
        String PATH;
        String url;

        switch (greenGoods) {
            case FRUIT:
                url = endpointConfig.FRUIT_STORE_URL;
                PATH = "product";
                break;
            case VEGETABLE:
                url = endpointConfig.VEGETABLE_STORE_URL;
                PATH = "item";
                break;
            default:
                throw new IllegalArgumentException("GreenGoods 에 url 이 할당되지 않았습니다.");
        }

        URI uri = UriComponentsBuilder.fromUriString(url).path(PATH).build().toUri();
        Map<String, List<String>> header = new Hashtable<>();
        header.put(HttpHeaders.AUTHORIZATION, List.of(authorization));
        Response response = HttpUtils.connect(new Request(uri, RequestMethod.GET, header));

        List<String> goodsList = new ArrayList<>();
        response.getJsonNodeBody().elements().forEachRemaining(jsonNode -> goodsList.add(jsonNode.asText()));

        return goodsList;
    }

    private String getAccessTokenFrom(String setCookieValue) {
        Pattern pattern = Pattern.compile("Authorization=([^;^ ]+)");
        Matcher matcher = pattern.matcher(setCookieValue);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("setCookie 가 패턴에 맞지 않습니다.");
        }
    }
}

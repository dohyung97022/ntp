package com.nt.ntp.service;

import com.nt.ntp.dto.response.GetPriceResponseDto;
import com.nt.ntp.service.factory.GreenGoodsWorkerFactory;
import com.nt.ntp.util.enums.GreenGoods;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class GreenGoodsService {
    private final GreenGoodsWorkerFactory greenGoodsWorkerFactory;
    private static final int COOKIE_DUE_DAY = 30 * 365;

    public void setCookieToAccessToken(GreenGoods greenGoods, HttpServletResponse httpServletResponse) throws IOException {
        String token = greenGoodsWorkerFactory.of(greenGoods).getAccessToken();

        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, token);
        cookie.setPath("/");
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(COOKIE_DUE_DAY));
        httpServletResponse.addCookie(cookie);
    }

    public List<String> getGoods(GreenGoods greenGoods, String authorization) throws IOException {
        return greenGoodsWorkerFactory.of(greenGoods).getGoods(authorization);
    }

    public GetPriceResponseDto getPrice(GreenGoods greenGoods, String authorization, String name) throws IOException {
        return greenGoodsWorkerFactory.of(greenGoods).getPrice(authorization, name);
    }
}

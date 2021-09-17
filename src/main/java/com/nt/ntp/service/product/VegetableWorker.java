package com.nt.ntp.service.product;

import com.nt.ntp.util.http.HttpUtils;
import com.nt.ntp.util.http.Request;
import com.nt.ntp.util.http.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class VegetableWorker extends GreenGoodsWorker {

    public VegetableWorker(String url) {
        super.URL = url;
        super.GET_GOODS_PATH = "item";
    }

    @Override
    public String getAccessToken() throws IOException {
        URI uri = UriComponentsBuilder.fromUriString(URL).path(ACCESS_TOKEN_PATH).build().toUri();
        Response response = HttpUtils.connect(new Request(uri));
        String setCookieValue = HttpUtils.joinHeaderValues(response.getHeader().get(HttpHeaders.SET_COOKIE));
        return HttpUtils.getAccessTokenFromSetCookieValue(setCookieValue);
    }
}

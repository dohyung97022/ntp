package com.nt.ntp.service.product;

import com.nt.ntp.util.http.HttpUtils;
import com.nt.ntp.util.http.Request;
import com.nt.ntp.util.http.Response;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class FruitWorker extends GreenGoodsWorker implements GreenGoodsWorkerImpl{
    static final String ACCESS_TOKEN_BODY_KEY = "accessToken";

    public FruitWorker(String url) {
        super.URL = url;
        super.GET_GOODS_PATH = "product";
    }

    @Override
    public String getAccessToken() throws IOException {
        URI uri = UriComponentsBuilder.fromUriString(URL).path(ACCESS_TOKEN_PATH).build().toUri();
        Response response = HttpUtils.connect(new Request(uri));
        return response.getJsonNodeBody().get(ACCESS_TOKEN_BODY_KEY).textValue();
    }
}

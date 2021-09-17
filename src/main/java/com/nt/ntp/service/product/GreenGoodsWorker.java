package com.nt.ntp.service.product;

import com.nt.ntp.dto.response.GetPriceResponseDto;
import com.nt.ntp.util.http.HttpUtils;
import com.nt.ntp.util.http.Request;
import com.nt.ntp.util.http.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GreenGoodsWorker implements GreenGoodsWorkerImpl{
    protected static final String ACCESS_TOKEN_PATH = "token";
    private static final String GET_PRICE_QUERY_NAME = "name";
    protected String GOODS_PATH;
    protected String URL;

    public String getAccessToken() throws IOException{
        throw new IllegalArgumentException("이 GreenGoodsWorker 는 GreenGoodsWorkerFactory 로 생성되지 않았습니다.");
    }

    public List<String> getGoods(String authorization) throws IOException{
        URI uri = UriComponentsBuilder.fromUriString(URL).path(GOODS_PATH).encode().build().toUri();
        Map<String, List<String>> header = new Hashtable<>();
        header.put(HttpHeaders.AUTHORIZATION, List.of(authorization));
        Response response = HttpUtils.connect(new Request(uri, RequestMethod.GET, header));

        List<String> goodsList = new ArrayList<>();
        response.getJsonNodeBody().elements().forEachRemaining(jsonNode -> goodsList.add(jsonNode.asText()));

        return goodsList;
    }

    public GetPriceResponseDto getPrice(String authorization, String name) throws IOException{
        URI uri = UriComponentsBuilder
                .fromUriString(URL).path(GOODS_PATH).queryParam(GET_PRICE_QUERY_NAME, name).encode().build().toUri();
        Map<String, List<String>> header = new Hashtable<>();
        header.put(HttpHeaders.AUTHORIZATION, List.of(authorization));
        Response response = HttpUtils.connect(new Request(uri, RequestMethod.GET, header));

        return new GetPriceResponseDto(response.getJsonNodeBody());
    }
}

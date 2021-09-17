package com.nt.ntp.service.product;

import com.nt.ntp.dto.response.GetPriceResponseDto;

import java.io.IOException;
import java.util.List;

public interface GreenGoodsWorkerImpl {
    String getAccessToken() throws IOException;
    List<String> getGoods(String authorization) throws IOException;
    GetPriceResponseDto getPrice(String authorization, String name) throws IOException;
}

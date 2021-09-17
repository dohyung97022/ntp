package com.nt.ntp.service.product;

import java.io.IOException;
import java.util.List;

public interface GreenGoodsWorkerImpl {
    String getAccessToken() throws IOException;
    List<String> getGoods(String authorization) throws IOException;
}

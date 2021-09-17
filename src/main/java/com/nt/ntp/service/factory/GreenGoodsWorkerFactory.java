package com.nt.ntp.service.factory;

import com.nt.ntp.config.EndpointConfig;
import com.nt.ntp.service.product.FruitWorker;
import com.nt.ntp.service.product.GreenGoodsWorker;
import com.nt.ntp.service.product.VegetableWorker;
import com.nt.ntp.util.enums.GreenGoods;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class GreenGoodsWorkerFactory {
    private final EndpointConfig endpointConfig;

    public GreenGoodsWorker of(GreenGoods type) {
        switch (type) {
            case FRUIT:
                return new FruitWorker(endpointConfig.FRUIT_STORE_URL);
            case VEGETABLE:
                return new VegetableWorker(endpointConfig.VEGETABLE_STORE_URL);
            default:
                throw new IllegalArgumentException("GreenGoods 에 url 이 할당되지 않았습니다.");
        }
    }
}

package com.nt.ntp.exception;

public class InvalidGreenGoodsOfFactoryException extends RuntimeException{

    public InvalidGreenGoodsOfFactoryException() {
        super("GreenGoodsFactory 에 할당된 GreenGoods 가 할당되지 않았거나 유효하지 않습니다.");
    }
}

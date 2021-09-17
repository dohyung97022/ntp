package com.nt.ntp.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class GetPriceResponseDto {
    private final String name;
    private final int price;

    public GetPriceResponseDto(JsonNode body){
        this.name = body.get("name").asText();
        this.price = body.get("price").asInt();
    }
}

package com.nt.ntp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetGoodsResponseDto {
    List<String> goods;
}

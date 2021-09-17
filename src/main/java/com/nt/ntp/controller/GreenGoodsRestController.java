package com.nt.ntp.controller;

import com.nt.ntp.dto.response.GetGoodsResponseDto;
import com.nt.ntp.dto.response.GetPriceResponseDto;
import com.nt.ntp.exception.EmptyRequestParamException;
import com.nt.ntp.service.GreenGoodsService;
import com.nt.ntp.util.enums.GreenGoods;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("api/green-goods")
@RestController
public class GreenGoodsRestController {

    private final GreenGoodsService greenGoodsService;

    @GetMapping("/token")
    public ResponseEntity<?> setCookieAsToken(@RequestParam("type") GreenGoods type, HttpServletResponse response) throws IOException {
        greenGoodsService.setCookieToAccessToken(type, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product")
    public ResponseEntity<GetGoodsResponseDto> getGoods
            (@RequestParam("type") GreenGoods type, @CookieValue(HttpHeaders.AUTHORIZATION) String token) throws IOException {
        List<String> products = greenGoodsService.getGoods(type, token);
        return ResponseEntity.ok().body(new GetGoodsResponseDto(products));
    }

    @GetMapping("/price")
    public ResponseEntity<GetPriceResponseDto> getPrice
            (@RequestParam GreenGoods type, @RequestParam String name, @CookieValue(HttpHeaders.AUTHORIZATION) String token) throws IOException {
        if (name.equals(""))
            throw new EmptyRequestParamException("name");
        return ResponseEntity.ok().body(greenGoodsService.getPrice(type, token, name));
    }
}

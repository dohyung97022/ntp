package com.nt.ntp.controller;

import com.nt.ntp.service.GreenGoodsService;
import com.nt.ntp.util.enums.GreenGoods;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}

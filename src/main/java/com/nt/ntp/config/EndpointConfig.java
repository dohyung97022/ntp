package com.nt.ntp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {
    @Value("${spring.fruit-store.url}")
    public String FRUIT_STORE_URL;
    @Value("${spring.vegetable-store.url}")
    public String VEGETABLE_STORE_URL;
}

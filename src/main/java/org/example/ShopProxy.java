package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class ShopProxy {

    private final RestTemplate restTemplate;

    public ShopProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}

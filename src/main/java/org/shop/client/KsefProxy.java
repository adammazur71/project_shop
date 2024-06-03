package org.shop.client;

import org.shop.dtos.KsefInvoiceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;

@Component
public class KsefProxy {

    WebClient webClient;
    @Value("${ksefUrl}")
    private String url;

    public KsefProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public KsefInvoiceDto sendInvoiceToKsef(KsefInvoiceDto ksefInvoiceDto) throws ExecutionException, InterruptedException {
        return webClient.post()
                .uri(url)
                .bodyValue(ksefInvoiceDto)
                .retrieve()
                .toEntity(KsefInvoiceDto.class)
                .toFuture()
                .get()
                .getBody();
    }

    public KsefInvoiceDto getInvoiceFromKsef(String id) throws ExecutionException, InterruptedException {
        return webClient.get()
                .uri(url + "/" + id)
                .retrieve()
                .toEntity(KsefInvoiceDto.class)
                .toFuture()
                .get()
                .getBody();
    }
}

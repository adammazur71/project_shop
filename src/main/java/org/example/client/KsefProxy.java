package org.example.client;

import org.example.dtos.KsefInvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;

@Component
public class KsefProxy {
    @Autowired
    WebClient webClient;
    @Value("${ksefUrl}")
    private String url;

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
    public KsefInvoiceDto getInvoiceFromKsef(Long id) throws ExecutionException, InterruptedException {
        return webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(KsefInvoiceDto.class)
                .toFuture()
                .get()
                .getBody();
    }
}

package org.example.client;

import org.example.dtos.KsefInvoiceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;
// TODO: JavaDoc
@Component
public class KsefProxy {

    // O tutaj też @Autowired, lepiej byłoby wstrzyknąć przez konstruktor. Autowired nie jest rekomendowany.

    WebClient webClient;

    public KsefProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${ksefUrl}")
    private String url;

    public KsefInvoiceDto sendInvoiceToKsef(KsefInvoiceDto ksefInvoiceDto) throws ExecutionException, InterruptedException {
        // a co jeśli ksefInvoiceDto jest nullem?
        // a co jeśli url jest nullem?
        // a co jeśli webClient jest nullem?
        // a co jeśli webClient.post() zwróci null?
        // a co jeśli pojawi się bład serwera?

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

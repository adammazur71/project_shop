package org.example.events;

import org.example.dtos.KsefInvoiceDto;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SentToKsefEventListener implements ApplicationListener<SentToKsefEvent> {
    @Override
    public void onApplicationEvent(SentToKsefEvent event) {
        KsefInvoiceDto ksefInvoiceDto = (KsefInvoiceDto) event.getSource();
        System.out.println("INVOICE NUMBER: " + ksefInvoiceDto.name() + " SENT TO KSEF. KSEF_ID IS: " + ksefInvoiceDto.id());
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

package org.shop.events;

import org.shop.dtos.KsefInvoiceDto;
import org.shop.simpleEmail.EmailServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SentToKsefEventListener implements ApplicationListener<SentToKsefEvent> {

    EmailServiceImpl emailService;

    public SentToKsefEventListener(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Override
    @Async("async-pool")
    public void onApplicationEvent(SentToKsefEvent event) {
        KsefInvoiceDto ksefInvoiceDto = (KsefInvoiceDto) event.getSource();
        String email = ksefInvoiceDto.data().customer().getEmail();
        String text = "Invoice no: " + ksefInvoiceDto.data().invoiceNo() + " has been sent to ksef. Ksef id is: " +
                ksefInvoiceDto.id();
        emailService.sendSimpleMessage(email, "Invoice has been sent to ksef", text);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

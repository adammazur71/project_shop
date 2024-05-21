package org.example.events;

import org.example.dtos.KsefInvoiceDto;
import org.example.simpleEmail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SentToKsefEventListener implements ApplicationListener<SentToKsefEvent> {
    @Autowired
    EmailServiceImpl emailService;

    @Override
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

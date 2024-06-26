package org.shop.scheduler;

import org.shop.dtos.InvoiceDto;
import org.shop.invoice.InvoiceService;
import org.shop.simpleEmail.EmailServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    private final InvoiceService invoiceService;
    private final EmailServiceImpl emailService;

    public Scheduler(InvoiceService invoiceService, EmailServiceImpl emailService) {
        this.invoiceService = invoiceService;
        this.emailService = emailService;
    }
    @Async("async-pool")
    @Scheduled(cron = "0 15 20 * * MON-FRI", zone = "Europe/Warsaw")
    protected void sendNotificationEmail() {
        List<InvoiceDto> unpaidInvoices = invoiceService.getUnpaidInvoices();
        for (InvoiceDto i : unpaidInvoices) {
            String email = i.customer().getEmail();
            String subject = "Unpaid invoice no: " + i.invoiceNo();
            String text = "Please settle the payment for invoice: " + i.invoiceNo() + " in the amount of PLN : " + i.grossAmount();
            emailService.sendSimpleMessage(email, subject, text);
        }
    }
}

package org.example.scheduler;

import org.example.dtos.InvoiceDto;
import org.example.invoice.InvoiceService;
import org.example.simpleEmail.EmailServiceImpl;
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

    @Scheduled(cron = "0 15 20 * * MON-FRI", zone = "Europe/Warsaw")
    private void sendNotificationEmail() {
        List<InvoiceDto> unpaidInvoices = invoiceService.getUnpaidInvoices();
        for (InvoiceDto i : unpaidInvoices) {
            String email = i.customer().getEmail();
            String subject = "Unpaid invoice no: " + i.invoiceNo();
            String text = "Please settle the payment for invoice: " + i.invoiceNo() + " in the amount of PLN : " + i.grossAmount();
            emailService.sendSimpleMessage(email, subject, text);
        }
    }
}

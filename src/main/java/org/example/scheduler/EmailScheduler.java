package org.example.scheduler;

import org.example.dtos.InvoiceDto;
import org.example.invoice.InvoiceService;
import org.example.simpleEmail.EmailServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduler {
    private final InvoiceService invoiceService;
    private final EmailServiceImpl emailService;

    public EmailScheduler(InvoiceService invoiceService, EmailServiceImpl emailService) {
        this.invoiceService = invoiceService;
        this.emailService = emailService;
    }

    // Cron expression - przeniósłbym do properties. Wtedy możne za pomoca adnotacji @Value przekazać wartość jako String i wykorzystać jako arguemnt metody Schedulera
    @Scheduled(cron = "${cron}", zone = "Europe/Warsaw")
    private void sendNotificationEmail() {
        List<InvoiceDto> unpaidInvoices = invoiceService.getUnpaidInvoices();
        for (InvoiceDto i : unpaidInvoices) {
            // A może event DTO?
            String email = i.customer().getEmail();
            String subject = "Unpaid invoice no: " + i.invoiceNo();
            String text = "Please settle the payment for invoice: " + i.invoiceNo() + " in the amount of PLN : " + i.grossAmount();
            emailService.sendSimpleMessage(email, subject, text);
        }
    }
}

package org.example.invoice;

import org.example.client.KsefProxy;
import org.example.dtos.InvoiceDto;
import org.example.dtos.KsefInvoiceDto;
import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.example.events.SentToKsefEvent;
import org.example.exceptions.ValidationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class InvoiceService {
    private final InvoiceRepository repository;
    private final KsefProxy proxy;
    private final KsefMapper ksefMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final InvoiceMapper invoiceMapper;

    public static final int PURCHASE_INVOICE = 0;
    public static final int SALES_INVOICE = 1;
    public static final int NOT_PAID_INVOICE = 0;

    public InvoiceService(InvoiceRepository repository, KsefProxy proxy, KsefMapper ksefMapper, ApplicationEventPublisher applicationEventPublisher, InvoiceMapper invoiceMapper) {
        this.repository = repository;
        this.proxy = proxy;
        this.ksefMapper = ksefMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.invoiceMapper = invoiceMapper;
    }

    public Invoice importInvoice(Invoice invoice) {
        invoice.setNetAmount(calculateNetAmount(invoice));
        invoice.setGrossAmount(calculateGrossAmount(invoice));
        return repository.importInvoice(invoice);
    }


    private Double calculateNetAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        Double calculatedNetAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedNetAmount = calculatedNetAmount + i.getNetValue() * i.getQtySold();
        }
        return calculatedNetAmount;
    }

    private Double calculateGrossAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        Double calculatedGrossAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedGrossAmount = calculatedGrossAmount + i.getGrossValue() * i.getQtySold();
        }
        return calculatedGrossAmount;
    }

    @Transactional
    public KsefInvoiceDto sendInvoiceToKsef(Long invoiceId) throws ExecutionException, InterruptedException {
        Invoice invoiceToSend = getInvoiceById(invoiceId);
        validateInvoiceBeforeSend(invoiceToSend);
        KsefInvoiceDto answerFromKsef = proxy.sendInvoiceToKsef(ksefMapper.toKsefInvoiceDto(invoiceToSend));
        setKsefId(invoiceToSend, answerFromKsef);
        repository.updateInvoice(invoiceToSend);
        SentToKsefEvent sentToKsefEvent = new SentToKsefEvent(answerFromKsef, Clock.systemUTC());
        applicationEventPublisher.publishEvent(sentToKsefEvent);
        return answerFromKsef;
    }

    private void validateInvoiceBeforeSend(Invoice invoice) {
        if (invoice.getKsefId() != null)
            throw new ValidationException("Invoice " + invoice.getInvoiceNo() + " has been already sent");
        if (invoice.getInvoiceType() != SALES_INVOICE)
            throw new ValidationException("It's not selling invoice");
    }

    private void setKsefId(Invoice invoice, KsefInvoiceDto answerFromKsef) {
        invoice.setKsefId(answerFromKsef.id());
    }

    private Invoice getInvoiceById(Long invoiceId) {
        return repository.getInvoiceById(invoiceId);
    }

    public KsefInvoiceDto getInvoiceFromKsef(String ksefId) throws ExecutionException, InterruptedException {
        return proxy.getInvoiceFromKsef(ksefId);
    }

    public List<InvoiceDto> getUnpaidInvoices() {
        List<Invoice> unpaidInvoices = repository.getInvoices(NOT_PAID_INVOICE, SALES_INVOICE);
        List<InvoiceDto> unpaidInvoicesDto = new ArrayList<>();
        for (Invoice i:unpaidInvoices
             ) {unpaidInvoicesDto.add(invoiceMapper.toDto(i));
        }
        return unpaidInvoicesDto;
    }
}

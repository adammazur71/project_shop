package org.shop.invoice;

import org.shop.client.KsefProxy;
import org.shop.dtos.InvoiceDto;
import org.shop.dtos.KsefInvoiceDto;
import org.shop.entities.Invoice;
import org.shop.events.SentToKsefEvent;
import org.shop.exceptions.ValidationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class InvoiceService {
    private final InvoiceRepository repository;
    private final KsefProxy proxy;
    private final KsefMapper ksefMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final InvoiceMapper invoiceMapper;
    private final Calculator calculator;

    public static final int PURCHASE_INVOICE = 0;
    public static final int SALES_INVOICE = 1;
    public static final int NOT_PAID_INVOICE = 0;

    public InvoiceService(InvoiceRepository repository, KsefProxy proxy, KsefMapper ksefMapper,
                          ApplicationEventPublisher applicationEventPublisher, InvoiceMapper invoiceMapper,
                          Calculator calculator) {
        this.repository = repository;
        this.proxy = proxy;
        this.ksefMapper = ksefMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.invoiceMapper = invoiceMapper;
        this.calculator = calculator;
    }

    public Invoice importInvoice(Invoice invoice) {
        Invoice invoiceToSave = calculator.setCalculatedGrossItemsPrice(invoice);
        invoice.setNetAmount(calculator.calculateNetInvoiceAmount(invoiceToSave));
        invoice.setGrossAmount(calculator.calculateGrossInvoiceAmount(invoiceToSave));
        return repository.importInvoice(invoiceToSave);
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
        for (Invoice i : unpaidInvoices
        ) {
            unpaidInvoicesDto.add(invoiceMapper.toDto(i));
        }
        return unpaidInvoicesDto;
    }
}

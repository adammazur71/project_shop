package org.example.invoice;

import org.example.client.KsefProxy;
import org.example.dtos.KsefInvoiceDto;
import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class InvoiceService {
    InvoiceRepository repository;
    KsefProxy proxy;
    KsefMapper ksefMapper;

    public static final int PURCHASE_INVOICE = 0;
    public static final int SALES_INVOICE = 1;

    public InvoiceService(InvoiceRepository repository, KsefProxy proxy, KsefMapper ksefMapper) {
        this.repository = repository;
        this.proxy = proxy;
        this.ksefMapper = ksefMapper;
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
        KsefInvoiceDto answerFromKsef = proxy.sendInvoiceToKsef(ksefMapper.toKsefInvoiceDto(invoiceToSend));
        setKsefId(invoiceToSend, answerFromKsef);
        repository.updateInvoice(invoiceToSend);
        return answerFromKsef;
    }

    private Invoice setKsefId(Invoice invoice, KsefInvoiceDto answerFromKsef) {
        invoice.setKsefId(answerFromKsef.id());
        return invoice;
    }

    private Invoice getInvoiceById(Long invoiceId) {
        return repository.getInvoiceById(invoiceId);
    }

    public KsefInvoiceDto getInvoiceFromKsef(String ksefId) throws ExecutionException, InterruptedException {
        return proxy.getInvoiceFromKsef(ksefId);
    }
}

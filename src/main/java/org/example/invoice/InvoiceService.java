package org.example.invoice;

import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.example.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceService {
    InvoiceRepository repository;

    private static final int PURCHASE_INVOICE = 0;
    private static final int SALES_INVOICE = 1;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public Invoice importInvoice(Invoice invoice) {
        if (invoice.getInvoiceType() != PURCHASE_INVOICE) throw new ValidationException("It's not purchase invoice");
        invoice.setNetAmount(calculateNetAmount(invoice));
        invoice.setGrossAmount(calculateGrossAmount(invoice));
        return repository.importInvoice(invoice);
    }

    private Double calculateNetAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        Double calculatedNetAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedNetAmount = calculatedNetAmount + i.getNetValue()*i.getQtySold();
        }
        return calculatedNetAmount;
    }

    private Double calculateGrossAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        Double calculatedGrossAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedGrossAmount = calculatedGrossAmount + i.getGrossValue()*i.getQtySold();
        }
        return calculatedGrossAmount;
    }


}

package org.example.invoice;

import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Calculator {
    protected Double calculateNetInvoiceAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        double calculatedNetAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedNetAmount = calculatedNetAmount + i.getNetValue() * i.getQtySold();
        }
        return calculatedNetAmount;
    }

    protected Double calculateGrossInvoiceAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        double calculatedGrossAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedGrossAmount = calculatedGrossAmount + i.getGrossValue() * i.getQtySold();
        }
        return calculatedGrossAmount;
    }

    protected Invoice setCalculatedGrossItemsPrice(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItem();
        items.forEach(s -> s.setGrossValue(calculateGrossPurchaseItemPrice(s)));
        invoice.setInvoiceItem(items);
        return invoice;
    }

    private Double calculateGrossPurchaseItemPrice(InvoiceItem item) {
        double vatStake = item.getVatStake();
        double netPrice = item.getNetValue();
        return (double) Math.round((netPrice + vatStake * netPrice) * 100) / 100;
    }
}

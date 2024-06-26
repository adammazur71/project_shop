package org.shop.invoice;

import org.shop.entities.Invoice;
import org.shop.entities.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Calculator {
    protected Double calculateNetInvoiceAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItems();
        double calculatedNetAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedNetAmount = Math.round((calculatedNetAmount + i.getNetValue() * i.getQtySold())*100)/100.0;
        }
        return calculatedNetAmount;
    }

    protected Double calculateGrossInvoiceAmount(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItems();
        double calculatedGrossAmount = 0.0;
        for (InvoiceItem i : items) {
            calculatedGrossAmount = Math.round((calculatedGrossAmount + i.getGrossValue() * i.getQtySold())*100)/100.0;
        }
        return calculatedGrossAmount;
    }

    protected Invoice setCalculatedGrossItemsPrice(Invoice invoice) {
        Set<InvoiceItem> items = invoice.getInvoiceItems();
        items.forEach(s -> s.setGrossValue(calculateGrossPurchaseItemPrice(s)));
        invoice.setInvoiceItems(items);
        return invoice;
    }

    private Double calculateGrossPurchaseItemPrice(InvoiceItem item) {
        double vatStake = item.getVatStake();
        double netPrice = item.getNetValue();
        return (double) Math.round((netPrice + vatStake * netPrice) * 100) / 100.0;
    }
}

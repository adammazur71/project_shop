package org.example.invoice;

import org.example.dtos.InvoiceDto;
import org.example.entieties.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public Invoice toEntity(InvoiceDto invoiceDto) {
        return new Invoice(invoiceDto.invoiceId(), invoiceDto.invoiceType(), invoiceDto.invoiceNo(), null,
                invoiceDto.ksefId(), null, null, invoiceDto.customer(), invoiceDto.invoiceItem());
    }

    public InvoiceDto toDto(Invoice invoice) {
        return new InvoiceDto(invoice.getInvoiceId(), invoice.getInvoiceType(), invoice.getInvoiceNo(), invoice.getShipments(),
                invoice.getKsefId(), invoice.getNetAmount(), invoice.getGrossAmount(), invoice.getCustomer(),
                invoice.getInvoiceItem());
    }
}

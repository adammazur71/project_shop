package org.example.invoice;

import org.example.dtos.InvoiceDto;
import org.example.entieties.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public Invoice toEntity(InvoiceDto invoiceDto) {
        return new Invoice(invoiceDto.invoiceId(), invoiceDto.invoiceType(), null, invoiceDto.ksefStatus(),
                null, null, invoiceDto.customer(), invoiceDto.invoiceItem());
    }
}

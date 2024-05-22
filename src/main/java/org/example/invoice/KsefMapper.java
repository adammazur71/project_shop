package org.example.invoice;

import org.example.dtos.KsefInvoiceDto;
import org.example.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// TODO: Javadoc
public class KsefMapper {

    @Autowired
    InvoiceMapper invoiceMapper;

    public KsefInvoiceDto toKsefInvoiceDto(Invoice invoice) {
        return new KsefInvoiceDto(null, null, invoice.getInvoiceNo(), invoiceMapper.toDto(invoice));
    }
}

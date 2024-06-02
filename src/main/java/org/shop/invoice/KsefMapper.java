package org.shop.invoice;

import org.shop.dtos.KsefInvoiceDto;
import org.shop.entieties.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KsefMapper {

    InvoiceMapper invoiceMapper;

    public KsefMapper(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    public KsefInvoiceDto toKsefInvoiceDto(Invoice invoice) {
        return new KsefInvoiceDto(null, null, invoice.getInvoiceNo(), invoiceMapper.toDto(invoice));
    }
}

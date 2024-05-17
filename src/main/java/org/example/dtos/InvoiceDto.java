package org.example.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.entieties.Customer;
import org.example.entieties.InvoiceItem;
import org.example.entieties.Shipments;

import java.util.Set;

public record InvoiceDto(Long invoiceId, @Min(0) @Max(1) Integer invoiceType, String invoiceNo,
                         Set<Shipments> shipments, @Min(0) @Max(1) String ksefId,
                         Double netAmount, Double grossAmount, @NotNull Customer customer,
                         @NotNull Set<InvoiceItem> invoiceItem) {
}

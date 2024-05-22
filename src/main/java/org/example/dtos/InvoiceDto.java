package org.example.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.entities.Customer;
import org.example.entities.InvoiceItem;
import org.example.entities.Shipments;

import java.util.Set;
// TODO: JAVA DOC
public record InvoiceDto(Long invoiceId, @Min(0) @Max(1) Integer invoiceType, String invoiceNo, Integer isPaid,
                         Set<Shipments> shipments, @Min(0) @Max(1) String ksefId,
                         Double netAmount, Double grossAmount, @NotNull Customer customer,
                         @NotNull Set<InvoiceItem> invoiceItem) {
}

package org.example.invoice;

import lombok.AllArgsConstructor;
import org.example.dtos.InvoiceDto;
import org.example.entieties.Invoice;
import org.example.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {
    InvoiceService service;
    InvoiceMapper mapper;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Invoice> importInvoice(@RequestBody InvoiceDto invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        Invoice importedInvoice = service.importInvoice(mapper.toEntity(invoice));
        return ResponseEntity.ok(importedInvoice);
    }
}

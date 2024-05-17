package org.example.invoice;

import lombok.AllArgsConstructor;
import org.example.dtos.InvoiceDto;
import org.example.dtos.KsefInvoiceDto;
import org.example.entieties.Invoice;
import org.example.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {
    InvoiceService service;
    InvoiceMapper mapper;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Invoice> consumeInvoice(@RequestBody InvoiceDto invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        Invoice importedInvoice = service.importInvoice(mapper.toEntity(invoice));
        return ResponseEntity.ok(importedInvoice);
    }

    @PostMapping(value = "/ksef/{invoiceId}", produces = "application/json")
    public ResponseEntity<KsefInvoiceDto> sendToKsef(@PathVariable Long invoiceId) throws ExecutionException, InterruptedException {
        KsefInvoiceDto invoiceSentToKsef = service.sendInvoiceToKsef(invoiceId);
        return ResponseEntity.ok(invoiceSentToKsef);
    }
    @GetMapping(value = "/ksef/{ksefId}", produces = "application/json")
    public ResponseEntity<KsefInvoiceDto> getFromKsef(@PathVariable String ksefId) throws ExecutionException, InterruptedException {
        KsefInvoiceDto invoiceFromKsef = service.getInvoiceFromKsef(ksefId);
        return ResponseEntity.ok(invoiceFromKsef);
    }
}

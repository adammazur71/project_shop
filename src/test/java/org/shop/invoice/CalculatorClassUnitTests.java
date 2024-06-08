package org.shop.invoice;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.entities.Invoice;
import org.shop.entities.InvoiceItem;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorClassUnitTests {

    Calculator calculator = new Calculator();

    @Test
    void shouldCalculateAndSetCorrectGrossItemPrice() {
        //GIVEN
        Set<InvoiceItem> invoiceItems = new HashSet<>();
        invoiceItems.add(new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, null));
        invoiceItems.add(new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, null));
        invoiceItems.add(new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, null));
        Invoice invoice = new Invoice(1L, 1, "112/05/2024", 1,
                null, null, null, null, null, invoiceItems);
        Set<InvoiceItem> invoiceItemsWithExpectedGrossValue = new HashSet<>();
        invoiceItemsWithExpectedGrossValue.add(new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, 24.07));
        invoiceItemsWithExpectedGrossValue.add(new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, 44.42));
        invoiceItemsWithExpectedGrossValue.add(new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, 0.91));
        Invoice expectedInvoice = new Invoice(1L, 1, "112/05/2024", 1,
                null, null, null, null, null, invoiceItemsWithExpectedGrossValue);
        //WHEN
        Invoice invoiceWithCalculatedInvoiceItems = calculator.setCalculatedGrossItemsPrice(invoice);
        Set<Double> setOfCalculatedGrossItemValues = invoiceWithCalculatedInvoiceItems.getInvoiceItems().stream()
                .map(InvoiceItem::getGrossValue)
                .collect(Collectors.toSet());
        Set<Double> setOfExpectedGrossItemValues = expectedInvoice.getInvoiceItems().stream()
                        .map(InvoiceItem::getGrossValue)
                                .collect(Collectors.toSet());
        //THEN
        assertThat(setOfCalculatedGrossItemValues).containsExactlyInAnyOrderElementsOf(setOfExpectedGrossItemValues);
        assertThat(invoiceWithCalculatedInvoiceItems)
                .usingRecursiveComparison()
                .isEqualTo(expectedInvoice);

    }


}

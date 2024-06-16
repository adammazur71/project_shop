package org.shop.invoice;

import org.junit.jupiter.api.Test;
import org.shop.entities.Invoice;
import org.shop.entities.InvoiceItem;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorClassUnitTests {

    Calculator calculator = new Calculator();

    @Test
    void shouldCalculateAndSetCorrectGrossItemPrice() {
        //GIVEN
        Set<InvoiceItem> invoiceItems = Set.of(
                new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, null),
                new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, null),
                new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, null));
        Invoice invoice = new Invoice(1L, 1, "112/05/2024", 1,
                null, null, null, null, null, invoiceItems);
        Set<InvoiceItem> invoiceItemsWithExpectedGrossValue = Set.of(
                new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, 24.07),
                new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, 44.42),
                new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, 0.91));
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

    @Test
    void shouldCalculateCorrectInvoiceNetAmount() {
        //GIVEN
        Set<InvoiceItem> invoiceItems = Set.of(
                new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, 24.07),
                new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, 44.42),
                new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, 0.91));
        Invoice invoice = new Invoice(1L, 1, "112/05/2024", 1,
                null, null, null, null, null, invoiceItems);
        Double expectedInvoiceNetAmount = 334.35 + 1372.18 + 893.62;
        //WHEN
        Double actualInvoiceNetAmount = calculator.calculateNetInvoiceAmount(invoice);
        //THEN
        assertThat(actualInvoiceNetAmount).isEqualTo(expectedInvoiceNetAmount);
    }

    @Test
    void shouldCalculateCorrectInvoiceGrossAmount() {
        //GIVEN
        Set<InvoiceItem> invoiceItems = Set.of(
                new InvoiceItem(1L, 1L, 1L, 15, 22.29, 0.08, 24.07),
                new InvoiceItem(2L, 2L, 1L, 38, 36.11, 0.23, 44.42),
                new InvoiceItem(3L, 3L, 1L, 982, 0.91, 0.00, 0.91));
        Invoice invoice = new Invoice(1L, 1, "112/05/2024", 1,
                null, null, null, null, null, invoiceItems);
        Double expectedInvoiceGrossAmount = 24.07 * 15 + 44.42 * 38 + 0.91 * 982;
        //WHEN
        Double actualInvoiceGrossAmount = calculator.calculateGrossInvoiceAmount(invoice);
        //THEN
        assertThat(actualInvoiceGrossAmount).isEqualTo(expectedInvoiceGrossAmount);
    }


}

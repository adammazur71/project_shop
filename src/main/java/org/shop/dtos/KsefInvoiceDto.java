package org.shop.dtos;

import java.sql.Timestamp;

public record KsefInvoiceDto(String id, Timestamp createdAt, String name, InvoiceDto data) {
}

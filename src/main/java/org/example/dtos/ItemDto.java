package org.example.dtos;

public record ItemDto(long itemId, long itemTypeId, String name, double purchasePrice, double purchaseVat,
                      double sellingPrise, double sellingVat, long supplierId ) {
}

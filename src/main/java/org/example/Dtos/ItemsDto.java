package org.example.Dtos;

public record ItemsDto(long itemId, long itemTypeId, String name, double purchasePrice, double purchaseVat, double sellingPrise, double sellingVat, long supplierId ) {
}

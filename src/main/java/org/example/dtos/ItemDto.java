package org.example.dtos;

import org.example.entities.Customer;
import org.example.entities.ItemType;

public record ItemDto(Long itemId, ItemType itemType, String itemName, Double purchaseNetPrice, Double purchaseGrossPrice,
                      Double sellingNetPrice, Double sellingGrossPrice, Customer customer) {
}

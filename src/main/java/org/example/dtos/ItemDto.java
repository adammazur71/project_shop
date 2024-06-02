package org.example.dtos;

import org.example.entieties.Customer;
import org.example.entieties.ItemType;

public record ItemDto(Long itemId, ItemType itemType, String itemName, Double purchaseNetPrice, Double vatStake,
                      Double purchaseGrossPrice,
                      Double sellingNetPrice, Double sellingGrossPrice, Customer customer) {
}

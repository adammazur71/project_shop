package org.shop.dtos;

import org.shop.entieties.Customer;
import org.shop.entieties.ItemType;

public record ItemDto(Long itemId, ItemType itemType, String itemName, Double purchaseNetPrice, Double vatStake,
                      Double purchaseGrossPrice,
                      Double sellingNetPrice, Double sellingGrossPrice, Customer customer) {
}

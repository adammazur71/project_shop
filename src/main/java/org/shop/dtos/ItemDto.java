package org.shop.dtos;

import org.shop.entities.Customer;
import org.shop.entities.ItemType;
import org.shop.entities.Stock;

public record ItemDto(Long itemId, ItemType itemType, String itemName, Double purchaseNetPrice, Double vatStake,
                      Double purchaseGrossPrice,
                      Double sellingNetPrice, Double sellingGrossPrice, Customer customer, StockDto stockDto) {
}

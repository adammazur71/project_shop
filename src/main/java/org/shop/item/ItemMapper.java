package org.shop.item;

import org.shop.dtos.ItemDto;
import org.shop.dtos.StockDto;
import org.shop.entities.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    ItemDto toDto(Item item) {
        return new ItemDto(item.getItemId(), item.getItemType(), item.getItemName(), item.getPurchaseNetPrice(),
                item.getVatStake(), item.getPurchaseGrossPrice(), item.getSellingGrossPrice(),
                item.getSellingGrossPrice(), item.getCustomer(),
                new StockDto(item.getStock().getStockId(), item.getStock().getQty()));
    }

    Item toEntity(ItemDto itemDto) {
        return new Item(itemDto.itemId(), itemDto.itemType(), itemDto.itemName(), itemDto.purchaseNetPrice(),
                itemDto.vatStake(), itemDto.purchaseGrossPrice(), itemDto.sellingNetPrice(),
                itemDto.sellingGrossPrice(), itemDto.customer(), null, null);
    }
}

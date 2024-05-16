package org.example.item;

import org.example.dtos.ItemDto;
import org.example.entieties.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    ItemDto toDto(Item item){
        return new ItemDto(item.getItemId(), item.getItemId(), item.getItemName(), item.getPurchaseNetPrice(), item.getPurchaseGrossPrice(),
                item.getSellingGrossPrice(), item.getSellingGrossPrice(), item.getCustomer().getCustomerId());
    }
//    Item item(ItemDto itemDto){
//        return new Item(itemDto.itemId(), itemDto.itemTypeId(), itemDto.name(), itemDto.purchasePrice(), itemDto.purchaseVat(),
//                itemDto.sellingPrise(), itemDto.sellingVat(), itemDto.supplierId())
//    }
}

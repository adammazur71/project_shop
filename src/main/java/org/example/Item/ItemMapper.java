package org.example.Item;

import org.example.Dtos.ItemDto;
import org.example.Entieties.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    ItemDto toDto(Item item){
        return new ItemDto(item.getItemId(), item.getItemId(), item.getItemName(), item.getPurchasePrice(), item.getPurchaseVat(),
                item.getSellingVat(), item.getSellingVat(), item.getCustomer().getCustomerId());
    }
//    Item item(ItemDto itemDto){
//        return new Item(itemDto.itemId(), itemDto.itemTypeId(), itemDto.name(), itemDto.purchasePrice(), itemDto.purchaseVat(),
//                itemDto.sellingPrise(), itemDto.sellingVat(), itemDto.supplierId())
//    }
}

package org.shop.item;

import org.shop.dtos.ItemTypeDto;
import org.shop.entieties.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeMapper {
    public ItemType toEntity(ItemTypeDto itemTypeDto) {
        return new ItemType(itemTypeDto.itemTypeId(), itemTypeDto.itemTypeName());
    }
}

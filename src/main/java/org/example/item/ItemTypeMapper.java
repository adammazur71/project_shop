package org.example.item;

import org.example.dtos.ItemTypeDto;
import org.example.entieties.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeMapper {
    public ItemType toEntity(ItemTypeDto itemTypeDto) {
        return new ItemType(itemTypeDto.itemTypeId(), itemTypeDto.ItemTypeName());
    }
}

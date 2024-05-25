package org.example.item;

import org.example.entities.Item;
import org.example.entities.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> finById(Long id);

    List<Item> findItemByName(String itemName);

    Item saveNewItem(Item item);

    ItemType makeNewItemType(ItemType itemType);
}

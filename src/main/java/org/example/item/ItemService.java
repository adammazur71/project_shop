package org.example.item;

import org.example.entities.Item;
import org.example.entities.ItemType;
import org.example.entities.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    private final StockRepository stockRepository;
    private final ItemTypeRepository itemTypeRepository;


    public ItemService(ItemRepository itemRepository, StockRepository stockRepository, ItemTypeRepository itemTypeRepository) {
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
        this.itemTypeRepository = itemTypeRepository;
    }

    public Optional<Item> finById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findItemByName(String itemName) {
        itemName = "%" + itemName + "%";
        return itemRepository.findItemByName(itemName);
    }

    public Item saveNewItem(Item item) {
        Item savedItem = itemRepository.save(item);
        stockRepository.save(new Stock(null, savedItem.getItemId(), 0));
        return savedItem;
    }

    public ItemType makeNewItemType(ItemType itemType) {
        return itemTypeRepository.save(itemType);
    }
}

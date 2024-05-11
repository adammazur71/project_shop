package org.example.Item;

import org.example.Entieties.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> finById(Long id) {
        return itemRepository.findById(id);
    }

    public Optional<List<Item>> findByItemName(String itemName){
        itemName = "%"+itemName+"%";
        return itemRepository.findByItemName(itemName);
    }
}

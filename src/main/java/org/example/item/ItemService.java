package org.example.item;

import org.example.entieties.Item;
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

    public List<Item> findByItemName(String itemName){
        itemName = "%"+itemName+"%";
        return itemRepository.findByItemName(itemName);
    }
}

package org.example.item;


import lombok.AllArgsConstructor;
import org.example.dtos.ItemDto;
import org.example.dtos.ItemTypeDto;
import org.example.entities.Item;
import org.example.entities.ItemType;
import org.example.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product/")
public class ItemController {

    private final ItemService service;
    private final ItemMapper mapper;
    private final ItemTypeMapper itemTypeMapper;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ItemDto> findItemById(@PathVariable Long id) {
        Item resultById = service.finById(id)
                .orElseThrow(() -> new NotFoundException("Id " + id + " is not found"));
        ItemDto result = mapper.toDto(resultById);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search/{itemName}", produces = "application/json")
    public ResponseEntity<List<ItemDto>> findItemByName(@PathVariable String itemName) {
        List<Item> itemlist = service.findItemByName(itemName);
        if (itemlist.size() == 0) {
            throw new NotFoundException("Item " + itemName + " is not found");
        } else {
            List<ItemDto> result = itemlist.stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Item> makeNewItem(@RequestBody ItemDto itemDto) {
        Item savedItem = service.saveNewItem(mapper.toEntity(itemDto));
        return ResponseEntity.ok(savedItem);
    }

    @PostMapping(value = "/type", produces = "application/json")
    public ResponseEntity<ItemType> makeNewItemType(@RequestBody ItemTypeDto itemTypeDto) {
        ItemType newItemType = service.makeNewItemType(itemTypeMapper.toEntity(itemTypeDto));
        return ResponseEntity.ok(newItemType);
    }

}

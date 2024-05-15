package org.example.item;


import lombok.AllArgsConstructor;
import org.example.dtos.ItemDto;
import org.example.entieties.Item;
import org.example.exceptions.IdNotFoundException;
import org.example.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ItemController {

    private final ItemService shopService;
    private final ItemMapper itemMapper;

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        Item resultById = shopService.finById(id)
                .orElseThrow(() -> new IdNotFoundException("Id " + id + " is not found"));
        ItemDto result = itemMapper.toDto(resultById);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/product/search/{itemName}", produces = "application/json")
    public ResponseEntity<List<ItemDto>> findByItemName(@PathVariable String itemName) {
        List<Item> itemlist = shopService.findByItemName(itemName);
        if (itemlist.size() == 0) {
            throw new ItemNotFoundException("Item " + itemName + " is not found");
        } else {
            List<ItemDto> result = itemlist.stream()
                    .map(itemMapper::toDto)
                    .toList();
            return ResponseEntity.ok(result);
        }
    }

}

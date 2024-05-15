package org.example.item;


import lombok.AllArgsConstructor;
import org.example.dtos.ItemDto;
import org.example.entieties.Item;
import org.example.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ItemController {

    private final ItemService service;
    private final ItemMapper mapper;

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<ItemDto> findItemById(@PathVariable Long id) {
        Item resultById = service.finById(id)
                .orElseThrow(() -> new NotFoundException("Id " + id + " is not found"));
        ItemDto result = mapper.toDto(resultById);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/product/search/{itemName}", produces = "application/json")
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

}

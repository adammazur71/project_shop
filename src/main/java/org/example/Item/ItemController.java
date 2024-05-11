package org.example.Item;


import lombok.AllArgsConstructor;
import org.example.Dtos.ItemDto;
import org.example.Entieties.Item;
import org.example.Exceptions.IdNotFoundException;
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

    @GetMapping
    public ResponseEntity<List<ItemDto>> findByItemName(String itemName){

    }

}

package org.example;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @PostMapping("/items")
    @ResponseBody
    public Items postItem(@RequestBody Items items) {
        return Items.builder()
                .amount(items.getAmount())
                .name(items.getName())
                .build();
    }
}

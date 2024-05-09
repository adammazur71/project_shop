package org.example;


import org.example.Exceptions.IdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShopController {

private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<Items> findById (@PathVariable Long id){
        Items resultById = shopService.finById(id)
                .orElseThrow(()->new IdNotFoundException("Id " + id + " is not found"));
        return ResponseEntity.ok(resultById);
    }

}

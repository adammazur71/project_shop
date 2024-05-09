package org.example;


import org.example.Dtos.ItemsDto;
import org.example.Entieties.Items;
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
    public ResponseEntity<ItemsDto> findById(@PathVariable Long id) {
        Items resultById = shopService.finById(id)
                .orElseThrow(() -> new IdNotFoundException("Id " + id + " is not found"));
        ItemsDto result =
                new ItemsDto(
                        resultById.getItemId(), resultById.getItemType().getItemTypeId(),
                        resultById.getName(), resultById.getPurchasePrice(), resultById.getPurchaseVat(),
                        resultById.getSellingPrice(), resultById.getSellingVat(),
                        resultById.getCustomer().getCustomerId());
        return ResponseEntity.ok(result);
    }

}

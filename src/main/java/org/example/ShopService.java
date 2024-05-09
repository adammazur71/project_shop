package org.example;

import org.example.Entieties.Items;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopService {
    private final ItemsRepository itemsRepository;
    private final ShopProxy shopProxy;


    public ShopService(ItemsRepository itemsRepository, ShopProxy shopProxy) {
        this.itemsRepository = itemsRepository;
        this.shopProxy = shopProxy;
    }

    public Optional<Items> finById(Long id) {
        return itemsRepository.findById(id);
    }
}

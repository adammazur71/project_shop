package org.example;

import org.example.Entieties.Items;
import org.springframework.data.repository.ListCrudRepository;

public interface ItemsRepository extends ListCrudRepository<Items, Long> {
}

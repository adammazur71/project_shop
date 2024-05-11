package org.example.Item;

import org.example.Entieties.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("FROM Item WHERE itemName like(:itemName)")
    Optional<List<Item>> findByItemName(@Param("itemName") String itemName);
}

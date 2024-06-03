package org.shop.item;

import org.shop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM item WHERE item_name like(:itemName)", nativeQuery = true)
    List<Item> findItemByName(@Param("itemName") String itemName);
}

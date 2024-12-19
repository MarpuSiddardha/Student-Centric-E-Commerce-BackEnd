package com.taskmanager.Repository;

import com.taskmanager.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemId(Long itemId); // Fetch item by its ID
}

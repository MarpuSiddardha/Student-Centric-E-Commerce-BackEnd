package com.taskmanager.Service;

import com.taskmanager.Entity.Item;
import com.taskmanager.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item) {
        return itemRepository.save(item); // Save the item to the database
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll(); // Fetch all items from the database
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id); // Find an item by ID
    }

    public List<Item> searchItemsByName(String name) {
        return itemRepository.findAll().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .toList(); // Filter items by name
    }
}

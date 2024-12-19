package com.taskmanager.Controller;

import com.taskmanager.Entity.Item;
import com.taskmanager.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Endpoint to add a new item
    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item savedItem = itemService.addItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // Endpoint to get all items
    @GetMapping("/view-all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Endpoint to search items by name
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String name) {
        List<Item> items = itemService.searchItemsByName(name);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Endpoint to get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }
}

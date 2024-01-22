package com.ijse.database.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.Item;
import com.ijse.database.service.ItemService;

@RestController
@CrossOrigin(origins = "*") //allowing cross origin to all
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/items") 
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.status(200).body(itemService.getAllItems());
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            return ResponseEntity.status(201).body(itemService.createItem(itemDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the Product");
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        if(item != null) {
            return ResponseEntity.status(HttpStatus.OK).body(item); //Return 200 with Body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404
        }
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemByIds(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        if(item != null) {
            return ResponseEntity.status(HttpStatus.OK).body(item); //Return 200 with Body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404
        }
    }
    
    @GetMapping("/item2/{id}")
    public ResponseEntity<Item> getItemById1(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        if(item != null) {
            return ResponseEntity.status(HttpStatus.OK).body(item); //Return 200 with Body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404
        }
    }
    

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(id, item);
    
        if (updatedItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    @GetMapping("/categories/{id}/items") 
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.getItemsByCategory(id));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Item deletedItem = itemService.deleteItem(id);

        if (deletedItem != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

 }

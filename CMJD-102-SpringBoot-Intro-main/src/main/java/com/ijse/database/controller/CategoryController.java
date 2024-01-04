package com.ijse.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.entity.Category;
import com.ijse.database.entity.Item;
import com.ijse.database.service.CategoryService;
import com.ijse.database.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/categories1/{id}")
    public Category getCategoryById1(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/categories2/{id}")
    public Category getCategoryById2(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PutMapping("/categories/{id}")
public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
    Category existingCategory = categoryService.findCategoryById(id);

    if (existingCategory != null) {
        try {
            // Update only non-null properties of the category
            if (updatedCategory.getCname() != null) {
                existingCategory.setCname(updatedCategory.getCname());
            }

            Category updatedCategoryResult = categoryService.updateCategory(id, existingCategory);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryResult);
        } catch (Exception e) {
            // Log or print the exception for debugging
            e.printStackTrace(); // or use a logger to log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        Category deletedCategory = categoryService.deleteCategory(id);

        if (deletedCategory != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @GetMapping("/categories/by-item/{itemId}")
public ResponseEntity<Category> getCategoryByItem(@PathVariable Long itemId) {
    // Retrieve the Item entity based on itemId
    Item item = itemService.getItemById(itemId); // Use itemId instead of id

    if (item != null) {
        Category category = categoryService.findCategoryByItem(item);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
}

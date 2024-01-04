package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;
import com.ijse.database.entity.Item;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category findCategoryById(Long id);
    Category findCategoryById1(Long id);
    Category findCategoryById2(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    Category deleteCategory(Long id);
    Category findCategoryByItem(Item item);
}

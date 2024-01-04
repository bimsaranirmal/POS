package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;
import com.ijse.database.entity.Item;
import com.ijse.database.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category findCategoryById1(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category findCategoryById2(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
    
        if (existingCategory != null) {
            try {
                // Check if the incoming category has a non-null Cname, if yes, update the name
                if (category.getCname() != null) {
                    existingCategory.setCname(category.getCname());
                }
                
                return categoryRepository.save(existingCategory);
            } catch (Exception e) {
                // Log or print the exception for debugging
                e.printStackTrace(); // or use a logger to log the exception
                return null;
            }
        } else {
            // Log or print a message indicating that the category with the given ID was not found
            System.out.println("Category not found with ID: " + id);
            return null;
        }
    }

    @Override
    public Category deleteCategory(Long id) {
        Category deletedCategory = categoryRepository.findById(id).orElse(null);

        if (deletedCategory != null) {
            categoryRepository.deleteById(id);
            return deletedCategory;
        } else {
            return null;
        }
    }

    @Override
    public Category findCategoryByItem(Item item) {
        return categoryRepository.findCategoryByItem(item);
    }
}

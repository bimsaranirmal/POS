package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.Category;
import com.ijse.database.entity.Item;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired 
    private CategoryRepository categoryRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItem1() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItem2() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(ItemDTO itemDTO) {
        Category category = categoryRepository.findById(itemDTO.getCategoryId()).orElse(null);

        if(category != null) {
            Item item = new Item();
            item.setName(itemDTO.getName());
            item.setPrice(itemDTO.getPrice());
            item.setQty(itemDTO.getQty());
            item.setCategory(category);
            
            return itemRepository.save(item);
        } else {
            return null;
        }

        
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
    @Override
    public Item getItemByIds(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item getItemById1(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if(existingItem != null) {
            existingItem.setName(item.getName());
           
            existingItem.setPrice(item.getPrice());
            existingItem.setQty(item.getQty());
            existingItem.setCategory(item.getCategory());
            
            return itemRepository.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getItemsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if(category != null) {
            return itemRepository.findProductsByCategory(category);
        } else {
            return null;
        }
    }

    @Override
    public Item deleteItem(Long id) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem != null) {
            itemRepository.delete(existingItem);
            return existingItem;
        } else {
            return null;
        }
    }
}

package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.Item;

@Service
public interface ItemService {
    List<Item> getAllItems();
    List<Item> getAllItem1();
    List<Item> getAllItem2();
    Item createItem(ItemDTO itemDTO);
    Item getItemById(Long id);
    Item getItemByIds(Long id);
    Item getItemById1(Long id);
    Item updateItem(Long id, Item item);
    List<Item> getItemsByCategory(Long id);
    Item deleteItem(Long id);
}

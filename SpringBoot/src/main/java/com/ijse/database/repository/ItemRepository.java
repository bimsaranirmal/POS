package com.ijse.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Category;
import com.ijse.database.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    //Custom Query to get products by category
    @Query("SELECT p FROM Item p WHERE p.category = :category")
    List<Item> findProductsByCategory(@Param("category") Category category);
    
}

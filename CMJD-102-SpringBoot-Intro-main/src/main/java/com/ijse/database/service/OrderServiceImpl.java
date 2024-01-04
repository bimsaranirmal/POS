package com.ijse.database.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.OrderDTO;
import com.ijse.database.entity.Order;
import com.ijse.database.entity.Item;
import com.ijse.database.repository.OrderRepository;

import jakarta.transaction.Transactional;

import com.ijse.database.repository.ItemRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
public Order createOrder(OrderDTO orderDTO) {
    Order order = new Order();
    Set<Item> itemsSet = new HashSet<>();
    order.setTotal(0.0);

    for (Long itemId : orderDTO.getItems()) {
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item != null && item.getQty() != 0) {
            itemsSet.add(item);
            order.setTotal(order.getTotal() + item.getPrice());
        }
    }

    Double tax = (order.getTotal() / 100) * 15;
    order.setTax(tax);
    order.setOrderTime(LocalDateTime.now());
    order.setItems(itemsSet);

    // Save the order along with its associated items
    return orderRepository.save(order);
}

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateItemQuantities(Set<Item> items) {
        for (Item item : items) {
            // Assuming that 'qty' is a field in the Item entity
            item.setQty(item.getQty() - 1);
            itemRepository.save(item);
            System.out.println("Updated quantity for item ID " + item.getId());
        }
    }
}

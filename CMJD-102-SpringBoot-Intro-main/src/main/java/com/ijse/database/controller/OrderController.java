package com.ijse.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.dto.OrderDTO;
import com.ijse.database.entity.Order;
import com.ijse.database.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // Create order and get the ordered items
            Order order = orderService.createOrder(orderDTO);

            // Update item quantities after placing the order
            orderService.updateItemQuantities(order.getItems());

            return ResponseEntity.status(201).body(order);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the order");
        }
    }
}


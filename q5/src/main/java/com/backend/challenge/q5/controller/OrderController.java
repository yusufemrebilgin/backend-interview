package com.backend.challenge.q5.controller;

import com.backend.challenge.q5.entity.Order;
import com.backend.challenge.q5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder/{customerId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long customerId, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(customerId, order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderForCode(orderId));
    }

    @GetMapping("/all")
    public List<Order> getAllOrdersForCustomer() {
        return orderService.getAllOrdersForCustomer();
    }
}

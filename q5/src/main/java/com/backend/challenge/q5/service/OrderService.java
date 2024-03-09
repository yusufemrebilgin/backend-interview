package com.backend.challenge.q5.service;

import com.backend.challenge.q5.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long customerId, Order order);
    Order getOrderForCode(Long orderId);
    List<Order> getAllOrdersForCustomer();
}

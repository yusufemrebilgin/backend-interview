package com.backend.challenge.q5.service.implementations;

import com.backend.challenge.q5.dao.CustomerRepository;
import com.backend.challenge.q5.dao.OrderRepository;
import com.backend.challenge.q5.entity.Cart;
import com.backend.challenge.q5.entity.Customer;
import com.backend.challenge.q5.entity.Order;
import com.backend.challenge.q5.entity.Product;
import com.backend.challenge.q5.service.CartService;
import com.backend.challenge.q5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    private CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order placeOrder(Long customerId, Order order) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Cart cart = customer.getCart();
            List<Product> products = order.getProducts();
            for (Product p: products)
                cartService.addProductToCart(customer.getId(), p, p.getQuantity());
        }
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderForCode(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrdersForCustomer() {
        return orderRepository.findAll();
    }
}

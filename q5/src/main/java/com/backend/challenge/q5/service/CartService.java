package com.backend.challenge.q5.service;

import com.backend.challenge.q5.entity.Cart;
import com.backend.challenge.q5.entity.Product;

public interface CartService {
    Cart getCart(Long id);
    Cart updateCart(Long customerId, Cart cart);
    void emptyCart(Long customerId);
    String addProductToCart(Long customerId, Product product, int productQuantity);
    void removeProductFromCart(Long customerId, Long productId);
}

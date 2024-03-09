package com.backend.challenge.q5.controller;

import com.backend.challenge.q5.entity.Cart;
import com.backend.challenge.q5.entity.Product;
import com.backend.challenge.q5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCart(customerId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long customerId, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(customerId, cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/empty-cart/{customerId}")
    public ResponseEntity<String> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.ok("Car emptied successfully");
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long customerId, @RequestBody Product product, int productQuantity) {
        return ResponseEntity.ok(cartService.addProductToCart(customerId, product, productQuantity));
    }

    @DeleteMapping("/remove-product/{customerId}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        cartService.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok("Product removed");
    }


}

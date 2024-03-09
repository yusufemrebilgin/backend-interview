package com.backend.challenge.q5.service.implementations;

import com.backend.challenge.q5.dao.CartRepository;
import com.backend.challenge.q5.dao.CustomerRepository;
import com.backend.challenge.q5.entity.Cart;
import com.backend.challenge.q5.entity.Customer;
import com.backend.challenge.q5.entity.Product;
import com.backend.challenge.q5.service.CartService;
import com.backend.challenge.q5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;

    private ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Cart updateCart(Long customerId, Cart cart) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            customer.getCart().setProducts(cart.getProducts());
        }
        return cartRepository.save(customer != null ? customer.getCart() : new Cart());
    }

    @Override
    public void emptyCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Cart cart = customer.getCart();
            cart.setProducts(null);
            cartRepository.save(cart);
        }
    }

    @Override
    public String addProductToCart(Long customerId, Product product, int productQuantity) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return "Customer not found with id: " + customerId;
        }
        Product productToBeAdded = productService.getProduct(product.getId());

        if (productToBeAdded != null && productToBeAdded.getQuantityInStock() > 0
                && productToBeAdded.getQuantityInStock() > productQuantity) {

            Cart cart = customer.getCart();
            for (int i = 0; i < productQuantity; i++) {
                cart.getProducts().add(product);
            }
            productToBeAdded.setQuantityInStock(productToBeAdded.getQuantityInStock() - productQuantity);
            productToBeAdded.setQuantity(productQuantity);
            calculateCartTotalPrice(cart);
            cartRepository.save(cart);
            return "Products added successfully..";
        } else {
            return "There is not enough stock for this product...";
        }
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return;

        Product product = productService.getProduct(productId);
        Cart cart = customer.getCart();
        cart.getProducts().remove(product);
        product.setQuantity(product.getQuantity() - 1);
        product.setQuantityInStock(product.getQuantityInStock() + 1);
        calculateCartTotalPrice(cart);
        cartRepository.save(cart);
    }

    private void calculateCartTotalPrice(Cart cart) {
        if (cart != null) {
            List<Product> products = cart.getProducts();
            double totalPrice = 0.0;
            for (Product p: products)
                totalPrice += p.getPrice() * p.getQuantity();
            cart.setTotalPrice(totalPrice);
        }
    }
}

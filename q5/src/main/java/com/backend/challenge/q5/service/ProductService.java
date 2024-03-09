package com.backend.challenge.q5.service;

import com.backend.challenge.q5.entity.Product;

public interface ProductService {
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}

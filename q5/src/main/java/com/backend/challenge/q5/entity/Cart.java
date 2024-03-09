package com.backend.challenge.q5.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = "total_price")
    private double totalPrice;

    public Cart() {}

    public Cart(List<Product> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
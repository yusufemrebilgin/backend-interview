package com.backend.challenge.q5.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product extends BaseEntity {

    @ManyToOne
    private Cart cart;
    private String name;
    private double price;
    private int quantity;
    private int quantityInStock;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public Product() {}

    public Product(Cart cart, String name, double price, int quantity, int quantityInStock, Order order) {
        this.cart = cart;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantityInStock = quantityInStock;
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

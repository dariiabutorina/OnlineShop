package com.internet.shop.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Long userId;
    private Long id;
    private List<Product> products;

    public Order(Long userId) {
        this.userId = userId;
        products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }
}

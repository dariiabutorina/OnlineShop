package com.internet.shop.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products;
    private Long id;
    private Long userId;

    public ShoppingCart(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
        products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid input data");
        }
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }
}

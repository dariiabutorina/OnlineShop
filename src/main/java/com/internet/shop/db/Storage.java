package com.internet.shop.db;

import com.internet.shop.models.Order;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> PRODUCTS = new ArrayList<>();
    public static final List<Order> ORDERS = new ArrayList<>();
    public static final List<ShoppingCart> SHOPPING_CARTS = new ArrayList<>();
    public static final List<User> USERS = new ArrayList<>();
    private static long productId = 0;

    public static void addProduct(Product product) {
        product.setId(++productId);
        PRODUCTS.add(product);
    }
}

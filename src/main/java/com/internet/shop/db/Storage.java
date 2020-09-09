package com.internet.shop.db;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    private static long productId = 0;
    private static long userId = 0;
    private static long orderId = 0;
    private static long shoppingCartId = 0;

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addUser(User user) {
        user.setId(++userId);
        users.add(user);
    }

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }
}

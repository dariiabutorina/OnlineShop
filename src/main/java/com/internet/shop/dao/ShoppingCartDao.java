package com.internet.shop.dao;

import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    Optional<ShoppingCart> getByUserId(Long userId);

    void clear(ShoppingCart shoppingCart);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteCart(ShoppingCart shoppingCart);
}

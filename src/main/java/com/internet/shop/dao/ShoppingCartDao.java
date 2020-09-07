package com.internet.shop.dao;

import com.internet.shop.models.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUserId(Long userId);

    void clear(ShoppingCart shoppingCart);

    boolean deleteCart(ShoppingCart shoppingCart);
}

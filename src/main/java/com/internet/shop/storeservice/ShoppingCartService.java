package com.internet.shop.storeservice;

import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart getByUserId(Long userId);

    void clear(ShoppingCart shoppingCart);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteCart(ShoppingCart shoppingCart);
}

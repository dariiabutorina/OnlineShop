package com.internet.shop.service.interfaces;

import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.service.GenericService;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart getByUserId(Long userId);

    void clear(ShoppingCart shoppingCart);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteCart(ShoppingCart shoppingCart);
}

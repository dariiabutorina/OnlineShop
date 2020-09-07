package com.internet.shop.dao.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.db.Storage;
import com.internet.shop.library.Dao;
import com.internet.shop.models.ShoppingCart;
import java.util.Optional;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(cart -> cart.getUserId()
                        .equals(userId))
                .findFirst();
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.clear();
    }

    @Override
    public boolean deleteCart(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts
                .removeIf(existingCart -> existingCart.getId()
                        .equals(shoppingCart.getId()));
    }
}

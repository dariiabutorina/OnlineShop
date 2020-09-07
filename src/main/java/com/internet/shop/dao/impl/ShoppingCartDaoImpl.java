package com.internet.shop.dao.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.db.Storage;
import com.internet.shop.library.Dao;
import com.internet.shop.models.ShoppingCart;
import java.util.Optional;
import java.util.stream.IntStream;

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
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(index -> Storage.shoppingCarts
                        .get(index)
                        .getId()
                        .equals(shoppingCart.getId()))
                .forEach(index -> Storage.shoppingCarts
                        .set(index, shoppingCart));
        return shoppingCart;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.clear();
        update(shoppingCart);
    }

    @Override
    public boolean deleteCart(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts
                .removeIf(existingCart -> existingCart.getId()
                        .equals(shoppingCart.getId()));
    }
}

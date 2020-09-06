package com.internet.shop.storeservice.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.storeservice.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.addProduct(shoppingCart, product);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).orElseThrow();
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.clear(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.deleteProduct(shoppingCart, product);
    }

    @Override
    public boolean deleteCart(ShoppingCart shoppingCart) {
        return shoppingCartDao.deleteCart(shoppingCart);
    }
}

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
        shoppingCart.addProduct(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).orElseThrow();
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean result = shoppingCart.getAllProducts()
                .removeIf(cartProduct -> cartProduct.getId()
                        .equals(product.getId()));
        shoppingCartDao.update(shoppingCart);
        return result;
    }

    @Override
    public boolean deleteCart(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart);
    }
}

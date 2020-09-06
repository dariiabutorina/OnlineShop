package com.internet.shop.storeservice.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.storeservice.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        return orderDao.completeOrder(shoppingCart);
    }

    @Override
    public Order get(Long id) {
        return orderDao.getById(id).orElseThrow();
    }

    @Override
    public List<Order> getUsersOrders(Long userId) {
        return orderDao.getUsersOrders(userId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }
}
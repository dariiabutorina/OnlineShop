package com.internet.shop.dao.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.db.Storage;
import com.internet.shop.library.Dao;
import com.internet.shop.models.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getId()
                        .equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getUsersOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId()
                        .equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.orders
                .removeIf(order -> order.getId()
                        .equals(id));
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }
}

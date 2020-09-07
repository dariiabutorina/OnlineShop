package com.internet.shop.dao;

import com.internet.shop.models.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> getById(Long id);

    Order update(Order order);

    List<Order> getUsersOrders(Long userId);

    List<Order> getAll();

    boolean deleteById(Long id);

    boolean delete(Order order);
}

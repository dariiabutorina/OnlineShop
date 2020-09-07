package com.internet.shop.storeservice;

import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    Order get(Long id);

    List<Order> getUsersOrders(Long userId);

    List<Order> getAll();

    boolean deleteById(Long id);

    boolean delete(Order order);
}

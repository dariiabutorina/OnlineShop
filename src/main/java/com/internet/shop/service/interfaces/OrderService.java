package com.internet.shop.service.interfaces;

import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.GenericService;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUsersOrders(Long userId);
}

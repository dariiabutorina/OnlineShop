package com.internet.shop.service.implementations;

import com.internet.shop.dao.interfaces.OrderDao;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.service.interfaces.OrderService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUserId());
        order.setProducts(List.copyOf(shoppingCart.getAllProducts()));
        orderDao.create(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
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
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }
}

package com.internet.shop.controller.order;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersOrdersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static final Long USER_ID = 1L;
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orders", orderService.getUsersOrders(USER_ID));
        req.getRequestDispatcher("/WEB-INF/views/orders/users.jsp").forward(req, resp);
    }
}

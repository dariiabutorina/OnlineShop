package com.internet.shop.controller.order;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.internet.shop.controller.LoginController.USER_ID;

public class GetUsersOrdersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        req.setAttribute("orders", orderService.getUsersOrders(userId));
        req.getRequestDispatcher("/WEB-INF/views/orders/users.jsp").forward(req, resp);
    }
}

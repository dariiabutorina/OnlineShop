package com.internet.shop.controller.order;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.OrderService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderController extends HttpServlet {
    private static final String SHOPPING_CART_ID = "cartId";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long cartId = Long.parseLong(req.getParameter(SHOPPING_CART_ID));
        orderService.completeOrder(shoppingCartService.get(cartId));
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}

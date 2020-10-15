package com.internet.shop.controller.order;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DeleteOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteOrderController.class);
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        orderService.deleteById(id);
        LOGGER.info("Deleted the order - " + orderService.get(id));
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}

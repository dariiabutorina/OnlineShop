package com.internet.shop.controller.order;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        List<Product> products = orderService.get(id).getProducts();
        BigDecimal sum = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.valueOf(0),BigDecimal::add);
        req.setAttribute("products", products);
        req.setAttribute("sum", sum);
        req.getRequestDispatcher("/WEB-INF/views/orders/details.jsp").forward(req, resp);
    }
}

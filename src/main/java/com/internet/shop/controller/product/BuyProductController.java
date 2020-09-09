package com.internet.shop.controller.product;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        shoppingCartService.getByUserId(USER_ID)
                .addProduct(productService.get(id));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}

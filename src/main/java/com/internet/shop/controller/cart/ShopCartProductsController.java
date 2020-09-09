package com.internet.shop.controller.cart;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopCartProductsController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", shoppingCartService.getByUserId(USER_ID).getAllProducts());
        req.getRequestDispatcher("/WEB-INF/views/carts/allProducts.jsp").forward(req, resp);
    }
}

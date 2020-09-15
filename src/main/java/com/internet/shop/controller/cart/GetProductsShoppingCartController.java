package com.internet.shop.controller.cart;

import static com.internet.shop.controller.LoginController.USER_ID;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductsShoppingCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        req.setAttribute("products", shoppingCartService.getByUserId(userId).getAllProducts());
        req.getRequestDispatcher("/WEB-INF/views/carts/allProducts.jsp").forward(req, resp);
    }
}

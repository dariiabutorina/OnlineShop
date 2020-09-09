package com.internet.shop.controller.cart;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopCartDelProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static final Long USER_ID = 1L;
    private static ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Product product = productService.get(productId);
        ShoppingCart usersCart = shoppingCartService.getByUserId(USER_ID);
        shoppingCartService.deleteProduct(usersCart, product);
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}

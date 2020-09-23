package com.internet.shop.controller.product;

import static com.internet.shop.controller.LoginController.USER_ID;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(userId),
                productService.get(productId));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}

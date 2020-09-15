package com.internet.shop.controller.cart;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.internet.shop.controller.LoginController.USER_ID;

public class DeleteProductShoppingCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Product product = productService.get(productId);
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart usersCart = shoppingCartService.getByUserId(userId);
        shoppingCartService.deleteProduct(usersCart, product);
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}

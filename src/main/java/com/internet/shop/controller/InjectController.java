package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectController extends HttpServlet {
    public static final Set<Role> USER_ROLE = Set.of(Role.of("USER"));
    public static final Set<Role> ADMIN_ROLE = Set.of(Role.of("ADMIN"));
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
        Creating two users
         */
        User firstUser =
                new User("Dariia", "DariiaPikul", "Admin007", ADMIN_ROLE);
        User secondUser =
                new User("Ashley", "halsey", "YungbludRocks789", USER_ROLE);
        User thirdUser =
                new User("Max", "maxim_dao", "BeautyBlogger456", USER_ROLE);
        userService.create(firstUser);
        userService.create(secondUser);
        userService.create(thirdUser);
        /*
        Creating shopping carts for the new users
         */
        ShoppingCart secondCart = new ShoppingCart(secondUser.getId());
        ShoppingCart thirdCart = new ShoppingCart(thirdUser.getId());
        shoppingCartService.create(secondCart);
        shoppingCartService.create(thirdCart);
        /*
        Redirecting to the home page
        */
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}

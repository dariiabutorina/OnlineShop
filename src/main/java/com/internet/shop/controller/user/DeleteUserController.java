package com.internet.shop.controller.user;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        if (userService.deleteById(id)) {
            shoppingCartService.delete(shoppingCartService.getByUserId(id));
        }
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
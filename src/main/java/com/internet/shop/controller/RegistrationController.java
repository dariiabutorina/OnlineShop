package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/security/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");

        if (name.length() < 5
                || login.length() < 5
                || password.length() < 5) {
            req.setAttribute("message",
                    "All the fields must be filled in containing at least 5 characters.");
            req.getRequestDispatcher("/WEB-INF/views/security/registration.jsp").forward(req, resp);
            return;
        }

        if (password.equals(passwordRepeat)) {
            User newUser = new User(name, login, password, Set.of(Role.of("USER")));
            User createdUser = userService.create(newUser);
            shoppingCartService.create(new ShoppingCart(createdUser.getId()));
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("message", "Passwords must be equal.");
            req.getRequestDispatcher("/WEB-INF/views/security/registration.jsp").forward(req, resp);
        }
    }
}

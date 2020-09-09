package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");

        if (name.length() == 0
                || login.length() == 0
                || password.length() == 0) {
            req.setAttribute("message", "All the fields must be filled in.");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }

        if (password.equals(passwordRepeat)) {
            User newUser = new User(name, login, password);
            userService.create(newUser);
            shoppingCartService.create(new ShoppingCart(newUser.getId()));
            resp.sendRedirect(req.getContextPath() + "/welcome");
        } else {
            req.setAttribute("message", "Passwords must be equal.");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}

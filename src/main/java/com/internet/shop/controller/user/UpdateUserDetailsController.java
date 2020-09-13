package com.internet.shop.controller.user;

import com.internet.shop.library.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private Long id = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        id = Long.parseLong(req.getParameter("id"));
        req.getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");

        if (name.length() == 0
                || password.length() == 0) {
            req.setAttribute("message", "All the fields must be filled in.");
            req.getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(req, resp);
        }

        if (password.equals(passwordRepeat)) {
            User updatingUser = userService.get(id);
            updatingUser.setName(name);
            updatingUser.setPassword(password);
            userService.update(updatingUser);
            resp.sendRedirect(req.getContextPath() + "/user/details?id=" + id);
        } else {
            req.setAttribute("message", "Passwords must be equal.");
            req.getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(req, resp);
        }
    }
}

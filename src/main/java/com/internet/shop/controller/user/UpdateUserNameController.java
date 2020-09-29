package com.internet.shop.controller.user;

import com.internet.shop.library.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserNameController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private Long id;

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

        if (name.length() < 5) {
            req.setAttribute("message",
                    "All the fields must be filled in containing at least 5 characters.");
            req.getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(req, resp);
        }

        User updatingUser = userService.get(id);
        updatingUser.setName(name);
        userService.update(updatingUser);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}

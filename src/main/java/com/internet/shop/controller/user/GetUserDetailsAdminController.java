package com.internet.shop.controller.user;

import static com.internet.shop.controller.LoginController.USER_ID;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserDetailsAdminController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId;
        try {
            userId = Long.parseLong(req.getParameter(USER_ID));
        } catch (NumberFormatException exception) {
            userId = (Long) req.getSession().getAttribute(USER_ID);
        }
        req.setAttribute("user", userService.get(userId));
        req.getRequestDispatcher("/WEB-INF/views/users/detailsAdmin.jsp").forward(req, resp);
    }
}

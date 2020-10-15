package com.internet.shop.controller.user;

import com.internet.shop.library.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DeleteUserController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteUserController.class);
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = userService.get(id);
        if (userService.delete(user)) {
            shoppingCartService.delete(shoppingCartService.getByUserId(id));
            LOGGER.info("The user - " + user + " was deleted.");
        }
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}

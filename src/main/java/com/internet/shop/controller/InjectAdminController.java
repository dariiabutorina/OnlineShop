package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class InjectAdminController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(InjectAdminController.class);
    private static final Set<Role> ADMIN = Set.of(Role.of("ADMIN"));
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService
            = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User admin = new User("Admin", "Admin", "Admin007", ADMIN);
        userService.create(admin);
        LOGGER.info("The admin - " + admin + " was created");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}

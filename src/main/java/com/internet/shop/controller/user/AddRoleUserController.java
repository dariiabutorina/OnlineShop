package com.internet.shop.controller.user;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRoleUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = Long.parseLong(req.getParameter("id"));
        Role role = Role.of(req.getParameter("test"));
        User user = userService.get(userId);
        Set<Role> roles = new HashSet<>(Set.copyOf(user.getRoles()));
        roles.add(role);
        user.setRoles(roles);
        userService.update(user);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}

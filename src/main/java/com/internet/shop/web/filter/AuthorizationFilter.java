package com.internet.shop.web.filter;

import static com.internet.shop.controller.LoginController.USER_ID;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls;

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls = new HashMap<>();
        /*
        Users only
        */
        protectedUrls.put("/product/buy", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/delete", List.of(Role.RoleName.USER));
        protectedUrls.put("/order/complete", List.of(Role.RoleName.USER));
        /*
        Admins only
        */
        protectedUrls.put("/users/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/all/admin", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/add", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/details/admin", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/role", List.of(Role.RoleName.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (!protectedUrls.containsKey(url)
                || isAuthorized(userService.get(userId), protectedUrls.get(url))) {
            filterChain.doFilter(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }

    private static boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(authorizedRoles::contains);
    }
}

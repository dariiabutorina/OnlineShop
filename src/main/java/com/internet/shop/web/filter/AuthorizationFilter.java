package com.internet.shop.web.filter;

import static com.internet.shop.controller.LoginController.USER_ID;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    public static final Set<Role.RoleName> USER = Set.of(Role.RoleName.USER);
    public static final Set<Role.RoleName> ADMIN = Set.of(Role.RoleName.ADMIN);
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private Map<String, Set<Role.RoleName>> protectedUrls;

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls = new HashMap<>();
        /*
        Users only
        */
        protectedUrls.put("/product/buy", USER);
        protectedUrls.put("/shopping-cart", USER);
        protectedUrls.put("/shopping-cart/delete", USER);
        protectedUrls.put("/order/complete", USER);
        /*
        Admins only
        */
        protectedUrls.put("/users/all", ADMIN);
        protectedUrls.put("/user/delete", ADMIN);
        protectedUrls.put("/products/all/admin", ADMIN);
        protectedUrls.put("/product/delete", ADMIN);
        protectedUrls.put("/product/add", ADMIN);
        protectedUrls.put("/orders/all", ADMIN);
        protectedUrls.put("/order/delete", ADMIN);
        protectedUrls.put("/user/details/admin", ADMIN);
        protectedUrls.put("/user/role", ADMIN);
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

    private static boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(authorizedRoles::contains);
    }
}

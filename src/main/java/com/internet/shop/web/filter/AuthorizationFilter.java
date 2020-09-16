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
    private static final Set<Role.RoleName> USER_ROLE = Set.of(Role.RoleName.USER);
    private static final Set<Role.RoleName> ADMIN_ROLE = Set.of(Role.RoleName.ADMIN);
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private Map<String, Set<Role.RoleName>> protectedUrls;

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls = new HashMap<>();
        /*
        Users only
        */
        protectedUrls.put("/product/buy", USER_ROLE);
        protectedUrls.put("/shopping-cart", USER_ROLE);
        protectedUrls.put("/shopping-cart/delete", USER_ROLE);
        protectedUrls.put("/order/complete", USER_ROLE);
        /*
        Admins only
        */
        protectedUrls.put("/users/all", ADMIN_ROLE);
        protectedUrls.put("/user/delete", ADMIN_ROLE);
        protectedUrls.put("/products/all/admin", ADMIN_ROLE);
        protectedUrls.put("/product/delete", ADMIN_ROLE);
        protectedUrls.put("/product/add", ADMIN_ROLE);
        protectedUrls.put("/orders/all", ADMIN_ROLE);
        protectedUrls.put("/order/delete", ADMIN_ROLE);
        protectedUrls.put("/user/details/admin", ADMIN_ROLE);
        protectedUrls.put("/user/role", ADMIN_ROLE);
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

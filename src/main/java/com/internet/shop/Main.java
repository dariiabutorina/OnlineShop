package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import com.internet.shop.util.HashUtil;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private static final byte[] salt = HashUtil.getSalt();

    public static void main(String[] args) {
        List<User> users = List.of(userService.get(1L), userService.get(2L), userService.get(3L));
        users.forEach(user -> user.setPassword(HashUtil.hashPassword(user.getPassword(), salt)));
        users.forEach(user -> user.setSalt(salt));
        users.forEach(userService::update);
    }
}

package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import com.internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        final String message = "Incorrect login or password.";
        Optional<User> existingUser = userService.findByLogin(login);
        if (existingUser.isEmpty()
                || isPasswordInvalid(password, existingUser.get())) {
            throw new AuthenticationException(message);
        }
        return existingUser.get();
    }

    static boolean isPasswordInvalid(String password, User userFromDB) {
        return !HashUtil.hashPassword(password,
                userFromDB.getSalt()).equals(userFromDB.getPassword());
    }
}

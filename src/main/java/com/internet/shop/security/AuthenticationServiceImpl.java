package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        final String message = "Incorrect login or password.";
        User existingUser = userService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException(message));
        if (existingUser.getPassword().equals(password)) {
            return existingUser;
        }
        throw new AuthenticationException(message);
    }
}

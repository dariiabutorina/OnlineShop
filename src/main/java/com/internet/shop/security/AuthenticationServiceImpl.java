package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
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
                || !existingUser.get().getPassword().equals(password)) {
            throw new AuthenticationException(message);
        }
        return existingUser.get();
    }
}

package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.model.User;
import com.internet.shop.util.HashUtil;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    static boolean isPasswordInvalid(String password, User userFromDB) {
        return !HashUtil.hashPassword(password,
                userFromDB.getSalt()).equals(userFromDB.getPassword());
    }
}

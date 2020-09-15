package com.internet.shop.service.interfaces;

import com.internet.shop.model.User;
import com.internet.shop.service.GenericService;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}

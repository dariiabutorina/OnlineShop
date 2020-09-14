package com.internet.shop.dao.interfaces;

import com.internet.shop.dao.GenericDao;
import com.internet.shop.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}

package com.internet.shop.dao;

import com.internet.shop.models.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    List<User> getAllUsers();

    boolean deleteById(Long id);

    boolean delete(User user);
}

package com.internet.shop.dao;

import com.internet.shop.models.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> getById(Long id);

    User update(User user);

    List<User> getAllProducts();

    boolean deleteById(Long id);

    boolean delete(User user);
}

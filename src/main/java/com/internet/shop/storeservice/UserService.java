package com.internet.shop.storeservice;

import com.internet.shop.models.User;
import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Long id);

    User update(User user);

    List<User> getAllProducts();

    boolean deleteById(Long id);

    boolean delete(User user);
}

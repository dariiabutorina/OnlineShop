package com.internet.shop.storeservice.impl;

import com.internet.shop.library.Service;
import com.internet.shop.models.User;
import com.internet.shop.storeservice.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAllProducts() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}

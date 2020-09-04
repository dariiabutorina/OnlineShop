package com.internet.shop.dao.impl;

import com.internet.shop.dao.UserDao;
import com.internet.shop.library.Dao;
import com.internet.shop.models.User;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
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

package com.internet.shop.dao.implementations;

import com.internet.shop.dao.interfaces.UserDao;
import com.internet.shop.db.Storage;
import com.internet.shop.library.Dao;
import com.internet.shop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(index -> Storage.users.get(index)
                        .getId().equals(user.getId()))
                .forEach(index -> Storage.users.set(index, user));
        return user;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.users
                .removeIf(user -> user.getId().equals(id));
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users.stream()
                .filter(existingUser -> existingUser.getLogin().equals(login))
                .findFirst();
    }
}

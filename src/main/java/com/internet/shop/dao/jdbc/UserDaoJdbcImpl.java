package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.UserDao;
import com.internet.shop.exceptions.DataBaseConnectionExchangeFailedException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM user WHERE deleted = false AND login = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractValue(resultSet));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the user "
                    + "with login: " + login, exception);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO user(name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to create the user: "
                    + user.getName(), exception);
        }
        return addRoles(user);
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM user WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractValue(resultSet));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the user "
                    + "with id: " + id, exception);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(extractValue(resultSet));
            }
            return users;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get data", exception);
        }
    }

    @Override
    public User update(User user) {
        Long userId = user.getId();
        String query = "UPDATE user SET name = ?, login = ?, password = ? WHERE id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, String.valueOf(userId));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to update the user "
                    + "with id: " + userId, exception);
        }
        deleteRoles(userId);
        return addRoles(user);
    }

    @Override
    public boolean deleteById(Long id) {
        deleteRoles(id);
        String query = "UPDATE user SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to delete the user "
                    + "with id: " + id, exception);
        }
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    private User addRoles(User user) {
        String query = "INSERT INTO user_role(id_user, id_role) "
                + "VALUES (?, (SELECT id FROM role WHERE name = ?))";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            for (Role role : user.getRoles()) {
                statement.setString(2, role.getRoleName().name());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to add the roles "
                    + "to the user: " + user.getName(), exception);
        }
        user.setRoles(getRoles(user.getId()));
        return user;
    }

    private User extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Set<Role> roles = getRoles(id);
        return new User(id, name, login, password, roles);
    }

    private Set<Role> getRoles(Long userId) {
        String query = "SELECT * FROM role r INNER JOIN user_role ur ON ur.id_role = r.id "
                + "WHERE ur.id_user = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("name"));
                role.setId(resultSet.getLong("id"));
                roles.add(role);
            }
            return roles;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the roles "
                    + "of the user with id: " + userId, exception);
        }
    }

    private boolean deleteRoles(Long userId) {
        String query = "DELETE FROM user_role WHERE id_user = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to delete the user's roles "
                    + "by user id: " + userId, exception);
        }
    }
}

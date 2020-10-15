package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.UserDao;
import com.internet.shop.exceptions.DatabaseDataExchangeFailedException;
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
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        String query = "SELECT * FROM user WHERE deleted = false AND login = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractValue(resultSet);
            }
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeFailedException("Failed to get the user "
                    + "with login: " + login, exception);
        }
        return addRolesToOptionalUser(user);
    }

    @Override
    public User create(User user) {
        logger.warn("Creating the user - " + user);
        String query = "INSERT INTO user(name, login, password, salt) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            logger.info("The user " + user + " was created successfully");
        } catch (SQLException exception) {
            String message = "Failed to create the user: " + user;
            logger.error(message, exception);
            throw new DatabaseDataExchangeFailedException(message, exception);
        }
        return addRoles(user);
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM user WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractValue(resultSet);
            }
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeFailedException("Failed to get the user "
                    + "with id: " + id, exception);
        }
        return addRolesToOptionalUser(user);
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
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeFailedException("Failed to get data", exception);
        }
        for (int index = 0; index < users.size(); index++) {
            users.get(index).setRoles(getRoles(users.get(index).getId()));
        }
        return users;
    }

    @Override
    public User update(User user) {
        logger.warn("Trying to update the user - " + user);
        Long userId = user.getId();
        String query = "UPDATE user SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.setString(5, String.valueOf(userId));
            statement.executeUpdate();
            logger.info("The user with id " + userId + " was updated successfully");
        } catch (SQLException exception) {
            String message = "Failed to update the user with id: " + userId;
            logger.error(message, exception);
            throw new DatabaseDataExchangeFailedException(message, exception);
        }
        deleteRoles(userId);
        return addRoles(user);
    }

    @Override
    public boolean deleteById(Long id) {
        logger.warn("Trying to delete the order with id " + id);
        String query = "UPDATE user SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() != 0) {
                logger.info("The user with id " + id + " was deleted successfully");
                return true;
            }
            return false;
        } catch (SQLException exception) {
            String message = "Failed to delete the user with id: " + id;
            logger.error(message, exception);
            throw new DatabaseDataExchangeFailedException(message, exception);
        }
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    private Optional<User> addRolesToOptionalUser(User user) {
        if (user != null) {
            user.setRoles(getRoles(user.getId()));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    private User addRoles(User user) {
        logger.warn("Trying to add the roles to the user - " + user);
        String query = "INSERT INTO user_role(id_user, id_role) "
                + "VALUES (?, (SELECT id FROM role WHERE name = ?))";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            for (Role role : user.getRoles()) {
                statement.setString(2, role.getRoleName().name());
                statement.executeUpdate();
            }
            logger.info("The roles were successfully added to the user: " + user);
        } catch (SQLException exception) {
            String message = "Failed to add the roles to the user: " + user;
            logger.error(message, exception);
            throw new DatabaseDataExchangeFailedException(message, exception);
        }
        user.setRoles(getRoles(user.getId()));
        return user;
    }

    private User extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        return new User(id, name, login, password, salt);
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
            throw new DatabaseDataExchangeFailedException("Failed to get the roles "
                    + "of the user with id: " + userId, exception);
        }
    }

    private void deleteRoles(Long userId) {
        logger.warn("Trying to delete the roles of the user with id " + userId);
        String query = "DELETE FROM user_role WHERE id_user = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            if (statement.executeUpdate() != 0) {
                logger.info("The roles of the user with id = " + userId
                        + " were successfully deleted");
            }
        } catch (SQLException exception) {
            String message = "Failed to delete the roles of the user with id: " + userId;
            logger.error(message, exception);
            throw new DatabaseDataExchangeFailedException(message, exception);
        }
    }
}

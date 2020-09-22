package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.OrderDao;
import com.internet.shop.exceptions.DataBaseConnectionExchangeFailedException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUsersOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(extractValue(resultSet));
            }
            return orders;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get data", exception);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders(uder_id) VALUE (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to create the order: "
                    + order.getId(), exception);
        }
        return addProducts(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractValue(resultSet));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the order "
                    + "with id: " + id, exception);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(extractValue(resultSet));
            }
            return orders;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get data", exception);
        }
    }

    @Override
    public Order update(Order order) {
        Long orderId = order.getId();
        String query = "UPDATE orders SET user_id = ? WHERE id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUserId());
            statement.setString(2, String.valueOf(orderId));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to update the order "
                    + "with id: " + orderId, exception);
        }
        deleteProducts(orderId);
        return addProducts(order);
    }

    @Override
    public boolean deleteById(Long id) {
        deleteProducts(id);
        String query;
        query = "UPDATE orders SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to delete the order "
                    + "with id: " + id, exception);
        }
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }

    private Order addProducts(Order order) {
        Long orderId = order.getId();
        String query = "INSERT INTO order_product(id_order, id_product) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to add the products "
                    + "in the order with id: " + orderId, exception);
        }
        order.setProducts(getProducts(orderId));
        return order;
    }

    private Order extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        List<Product> products = getProducts(id);
        return new Order(id, userId, products);
    }

    private List<Product> getProducts(Long orderId) {
        String query = "SELECT * FROM product p INNER JOIN order_product op "
                + "ON op.id_product = p.id WHERE op.id_order = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the products "
                    + "of the order with id: " + orderId, exception);
        }
    }

    private boolean deleteProducts(Long orderId) {
        String query = "DELETE FROM order_product WHERE id_order = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to delete the order's "
                    + "products with id: " + orderId, exception);
        }
    }
}

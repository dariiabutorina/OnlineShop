package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.OrderDao;
import com.internet.shop.exceptions.DatabaseDataExchangeException;
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
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

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
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to get data", exception);
        }
        return fillListOfOrdersWithProducts(orders);
    }

    @Override
    public Order create(Order order) {
        logger.warn("Creating the order - " + order);
        String query = "INSERT INTO orders(user_id) VALUE (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            logger.info("The order " + order + " was created successfully");
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to create the order: "
                    + order, exception);
        }
        return addProducts(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = extractValue(resultSet);
            }
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to get the order "
                    + "with id: " + id, exception);
        }
        return fillOrderWithProducts(order);
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
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to get data", exception);
        }
        return fillListOfOrdersWithProducts(orders);
    }

    @Override
    public Order update(Order order) {
        logger.warn("Trying to update the order - " + order);
        Long orderId = order.getId();
        String query = "UPDATE orders SET user_id = ? WHERE id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, orderId);
            statement.executeUpdate();
            logger.info("The order with id " + orderId + " was updated successfully");
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to update the order with id: "
                    + orderId, exception);
        }
        deleteProducts(orderId);
        return addProducts(order);
    }

    @Override
    public boolean deleteById(Long id) {
        logger.warn("Trying to delete the order with id " + id);
        String query = "UPDATE orders SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() != 0) {
                logger.info("The order with id " + id + " was deleted successfully");
                return true;
            }
            return false;
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to delete the order with id: "
                    + id, exception);
        }
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }

    private Order addProducts(Order order) {
        logger.warn("Trying to add the products to the order - " + order);
        Long orderId = order.getId();
        String query = "INSERT INTO order_product(id_order, id_product) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            logger.info("The products were successfully added to the order with id: " + orderId);
            return order;
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to add the products in the order "
                    + "with id: " + orderId, exception);
        }
    }

    private Optional<Order> fillOrderWithProducts(Order order) {
        if (order != null) {
            order.setProducts(getProducts(order.getId()));
            return Optional.of(order);
        }
        return Optional.empty();
    }

    private List<Order> fillListOfOrdersWithProducts(List<Order> orders) {
        for (int index = 0; index < orders.size(); index++) {
            orders.get(index).setProducts(getProducts(orders.get(index).getId()));
        }
        return orders;
    }

    private Order extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        return new Order(id, userId);
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
            throw new DatabaseDataExchangeException("Failed to get the products "
                    + "of the order with id: " + orderId, exception);
        }
    }

    private void deleteProducts(Long orderId) {
        logger.warn("Trying to delete the products of the order with id " + orderId);
        String query = "DELETE FROM order_product WHERE id_order = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            if (statement.executeUpdate() != 0) {
                logger.info("The products of the order with id = " + orderId
                        + " were successfully deleted");
            }
        } catch (SQLException exception) {
            throw new DatabaseDataExchangeException("Failed to delete the products "
                    + "from the order with id: " + orderId, exception);
        }
    }
}

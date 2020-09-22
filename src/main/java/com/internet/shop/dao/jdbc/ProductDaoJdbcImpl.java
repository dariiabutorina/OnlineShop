package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.ProductDao;
import com.internet.shop.exceptions.DataBaseConnectionExchangeFailedException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        String query = "INSERT INTO product(name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to create the product: "
                    + product.getName(), exception);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM product WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractValue(resultSet));
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get the product "
                    + "with id: " + id, exception);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractValue(resultSet);
                products.add(product);
            }
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to get data", exception);
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE product SET name = ?, price = ? WHERE id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setString(2, String.valueOf(product.getPrice()));
            statement.setString(3, String.valueOf(product.getId()));
            statement.executeUpdate();
            return product;
        } catch (SQLException exception) {
            throw new DataBaseConnectionExchangeFailedException("Failed to update the product: "
                    + product.getName(), exception);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE product SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(id));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataBaseConnectionExchangeFailedException("Failed to delete the product"
                    + " with id: " + id, e);
        }
    }

    @Override
    public boolean delete(Product product) {
        return deleteById(product.getId());
    }

    private Product extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        BigDecimal price = resultSet.getBigDecimal("price");
        Product product = new Product(name, price);
        product.setId(id);
        return product;
    }
}

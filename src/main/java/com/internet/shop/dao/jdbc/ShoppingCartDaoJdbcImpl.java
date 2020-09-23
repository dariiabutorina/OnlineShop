package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.ShoppingCartDao;
import com.internet.shop.exceptions.DataBaseDataExchangeFailedException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
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
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart shoppingCart = null;
        String query = "SELECT * FROM shopping_cart WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = extractValue(resultSet);
            }
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to get data", exception);
        }
        return fillShoppingCartWithProducts(shoppingCart);
    }

    private Optional<ShoppingCart> fillShoppingCartWithProducts(ShoppingCart shoppingCart) {
        if (shoppingCart != null) {
            shoppingCart.setProducts(getProducts(shoppingCart.getId()));
            return Optional.of(shoppingCart);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_cart(user_id) VALUE (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to create "
                    + "the shopping cart: " + shoppingCart.getId(), exception);
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        ShoppingCart shoppingCart = null;
        String query = "SELECT * FROM shopping_cart WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = extractValue(resultSet);
            }
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to get the shopping cart "
                    + "with id: " + id, exception);
        }
        return fillShoppingCartWithProducts(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        String query = "SELECT * FROM shopping_cart WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(extractValue(resultSet));
            }
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to get data", exception);
        }
        return fillListOfCartsWithProducts(shoppingCarts);
    }

    private List<ShoppingCart> fillListOfCartsWithProducts(List<ShoppingCart> shoppingCarts) {
        for (int index = 0; index < shoppingCarts.size(); index++) {
            shoppingCarts.get(index).setProducts(getProducts(shoppingCarts.get(index).getId()));
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        Long shoppingCartId = shoppingCart.getId();
        String query = "UPDATE shopping_cart SET user_id = ? WHERE id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, shoppingCart.getUserId());
            statement.setString(2, String.valueOf(shoppingCartId));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to update "
                    + "the shopping cart with id: " + shoppingCartId, exception);
        }
        deleteProducts(shoppingCartId);
        return addProducts(shoppingCart);
    }

    @Override
    public boolean deleteById(Long id) {
        deleteProducts(id);
        String query = "UPDATE shopping_cart SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to delete "
                    + "the shopping cart with id: " + id, exception);
        }
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return deleteById(shoppingCart.getId());
    }

    private ShoppingCart addProducts(ShoppingCart shoppingCart) {
        Long shoppingCartId = shoppingCart.getId();
        String query = "INSERT INTO shopping_cart_product VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            for (Product product : shoppingCart.getAllProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return shoppingCart;
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to add the products "
                    + "to the shopping cart with id: " + shoppingCartId, exception);
        }
    }

    private ShoppingCart extractValue(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        return new ShoppingCart(id, userId);
    }

    private List<Product> getProducts(Long shoppingCartId) {
        String query = "SELECT * FROM product p INNER JOIN shopping_cart_product scp "
                + "ON scp.id_product = p.id WHERE scp.id_shopping_cart = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
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
            throw new DataBaseDataExchangeFailedException("Failed to get the products "
                    + "from the shopping cart with id: " + shoppingCartId, exception);
        }
    }

    private boolean deleteProducts(Long shoppingCartId) {
        String query = "DELETE FROM shopping_cart_product WHERE id_shopping_cart = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, shoppingCartId);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataBaseDataExchangeFailedException("Failed to delete "
                    + "the shopping cart's products with id: " + shoppingCartId, exception);
        }
    }
}

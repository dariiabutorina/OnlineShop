package com.internet.shop.dao;

import com.internet.shop.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product create(Product product);

    Optional<Product> getById(Long id);

    Product update(Product product);

    List<Product> getAll();

    boolean deleteById(Long id);

    boolean delete(Product product);
}

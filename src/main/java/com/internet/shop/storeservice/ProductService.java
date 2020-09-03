package com.internet.shop.storeservice;

import com.internet.shop.models.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    Product update(Product product);

    List<Product> getAll();

    boolean deleteById(Long id);

    boolean delete(Product product);
}

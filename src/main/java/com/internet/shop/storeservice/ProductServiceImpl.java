package com.internet.shop.storeservice;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.models.Product;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id).orElseThrow();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean deleteById(Long id) {
        return productDao.deleteById(id);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }
}

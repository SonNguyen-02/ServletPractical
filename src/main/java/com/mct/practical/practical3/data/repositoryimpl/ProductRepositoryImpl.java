package com.mct.practical.practical3.data.repositoryimpl;

import com.mct.practical.practical3.data.server.dao.ProductDao;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDao productDao;

    public ProductRepositoryImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public long createProduct(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean removeProduct(Product product) {
        return productDao.delete(product);
    }

    @Override
    public boolean removeProduct(int id) {
        return productDao.delete(id);
    }

    @Override
    public long getTotalProductBySearch(String search, Integer[] categoryIds) {
        return productDao.getTotalProductBySearch(search, categoryIds);
    }

    @Override
    public List<Product> getProducts(String search, Integer[] categoryIds, int limit, int offset) {
        return productDao.getProducts(search, categoryIds, limit, offset);
    }

    @Override
    public Product getProduct(long id) {
        return productDao.getProduct(id);
    }
}

package com.mct.practical.practical3.domain.repository;

import com.mct.practical.practical3.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    long createProduct(Product product);

    boolean updateProduct(Product product);

    boolean removeProduct(Product product);

    boolean removeProduct(int id);

    long getTotalProductBySearch(String search, Integer[] categoryIds);

    List<Product> getProducts(String search, Integer[] categoryIds, int limit, int offset);

    Product getProduct(long id);

}

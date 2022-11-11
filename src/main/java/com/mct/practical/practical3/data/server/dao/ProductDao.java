package com.mct.practical.practical3.data.server.dao;

import com.mct.practical.practical3.domain.model.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    long getTotalProductBySearch(String search, Integer[] categoryIds);

    List<Product> getProducts(String search, Integer[] categoryIds, int limit, int offset);

    Product getProduct(long id);

}

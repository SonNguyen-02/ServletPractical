package com.mct.practical.practical3.domain.use_cases.product;

import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

import java.util.List;

public class GetProducts {

    private final ProductRepository productRepository;

    public GetProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public long count(String search, Integer[] categoryIds) {
        return productRepository.getTotalProductBySearch(search, categoryIds);
    }


    public List<Product> invoke(String search, Integer[] categoryIds, int limit, int offset) {
        return productRepository.getProducts(search, categoryIds, limit, offset);
    }

}

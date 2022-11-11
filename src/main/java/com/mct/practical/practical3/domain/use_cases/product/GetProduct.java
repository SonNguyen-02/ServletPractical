package com.mct.practical.practical3.domain.use_cases.product;

import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

public class GetProduct {

    private final ProductRepository productRepository;

    public GetProduct(ProductRepository categoryRepository) {
        this.productRepository = categoryRepository;
    }

    public Product invoke(long id) {
        return productRepository.getProduct(id);
    }

}

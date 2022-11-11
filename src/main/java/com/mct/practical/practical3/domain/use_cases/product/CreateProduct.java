package com.mct.practical.practical3.domain.use_cases.product;

import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

public class CreateProduct {

    private final ProductRepository productRepository;

    public CreateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long invoke(Product product) {
        return productRepository.createProduct(product);
    }
}

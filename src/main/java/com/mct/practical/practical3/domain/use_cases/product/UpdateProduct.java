package com.mct.practical.practical3.domain.use_cases.product;

import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

public class UpdateProduct {

    private final ProductRepository productRepository;

    public UpdateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean invoke(Product product) {
        return productRepository.updateProduct(product);
    }
}
